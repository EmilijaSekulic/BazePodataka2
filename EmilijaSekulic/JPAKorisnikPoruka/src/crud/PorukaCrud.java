package crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.SKorisnik;
import model.SPoruka;
import utils.PersistenceUtil;

public class PorukaCrud {

	public boolean unesiPoruku(SKorisnik posiljalac, SKorisnik primalac, SPoruka p) {

		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;

		boolean uspesno = false;

		try {

			et = em.getTransaction();
			et.begin();

			p.setSkorisnik1(posiljalac);
			p.setSkorisnik2(primalac);
			em.persist(p);

			em.flush();
			et.commit();

			uspesno = true;

		} catch (Exception ex) {

			ex.printStackTrace();

			if (et != null) {
				et.rollback();
			}
		} finally {

			if (em != null) {

				em.close();
			}
		}

		return uspesno;
	}

	public List<SPoruka> listaPoruka() {

		EntityManager em = utils.PersistenceUtil.getEntityManager();

		String upit = "select p from Sporuka p order by p.porukaId";

		List<SPoruka> poruke = em.createQuery(upit).getResultList();

		em.close();

		return poruke;
	}

	public List<SPoruka> listaPorukaKorisnika(SKorisnik k) {

		EntityManager em = utils.PersistenceUtil.getEntityManager();

		String upit = "select p from SPoruka p where p.skorisnik1 = :x";
		
		Query q = em.createQuery(upit);
		q.setParameter("x", k);
		
		List<SPoruka> poruke = q.getResultList();

		em.close();

		return poruke;

	}
}
