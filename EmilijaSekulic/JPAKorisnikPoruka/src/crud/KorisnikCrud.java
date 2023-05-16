package crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.SKorisnik;
import model.SPoruka;
import utils.PersistenceUtil;

public class KorisnikCrud {

	public boolean unesiKorisnika(SKorisnik k) {
		
		EntityManager em = utils.PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean uspesno = false;
		
		try {
			 
			et = em.getTransaction();
			et.begin();
			
			em.persist(k);
			
			em.flush();
			et.commit();
			
			uspesno = true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(et != null) {
				et.rollback();
			}
		} finally {
			
			if(em != null) {
				
				em.close();
			}
		}
		
		return uspesno;
	}
	
	public List<SKorisnik> listaKorisnika(){
		
		EntityManager em = utils.PersistenceUtil.getEntityManager();

		String upit = "select k from SKorisnik k order by k.korisnikId";
		
		List<SKorisnik> korisnici = em.createQuery(upit).getResultList();
		
		em.close();
		
		return korisnici;
	}
	
	public boolean poveziPorukuIKorisnika(SKorisnik kSalje, SKorisnik kPrima, SPoruka p) {
		

		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean uspesno = false;
		
		try {
			 
			et = em.getTransaction();
			et.begin();
			
			kSalje = em.merge(kSalje);
			kPrima = em.merge(kPrima);
			p = em.merge(p);
			
			p.setSkorisnik1(kSalje);
			p.setSkorisnik2(kPrima);
			kSalje.addSporukas1(p);
			kPrima.addSporukas2(p);
			
			em.merge(p);
			em.merge(kSalje);
			em.merge(kPrima);
			
			em.flush();
			et.commit();
			
			uspesno = true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(et != null) {
				et.rollback();
			}
		} finally {
			
			if(em != null) {
				
				em.close();
			}
		}
		
		return uspesno;
	}
	
	public boolean izbrisiKorisnika(SKorisnik k) {
		
		EntityManager em = utils.PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean uspesno = false;
		
		try {
			 
			et = em.getTransaction();
			et.begin();
			
			k = em.merge(k);
			
			List<SPoruka> porukeSalje = k.getSporukas1();
			porukeSalje.size();
			
			List<SPoruka> porukePrima = k.getSporukas2();
			porukePrima.size();
			
			for(SPoruka p1 : porukeSalje) {
				
				em.remove(p1);
			}
			
			em.merge(k);
			
			for(SPoruka p2 : porukePrima) {
				
				em.remove(p2);
			}
			
			em.merge(k);
			em.remove(k);
			
			em.flush();
			et.commit();
			
			uspesno = true;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			if(et != null) {
				et.rollback();
			}
		} finally {
			
			if(em != null) {
				
				em.close();
			}
		}
		
		return uspesno;
	}
	
	public SKorisnik pronadjiKorisnika(int idK) {
		
		EntityManager em = utils.PersistenceUtil.getEntityManager();
		
		SKorisnik k = em.find(SKorisnik.class, idK);
		
		em.close();
		
		return k;
	}
}
