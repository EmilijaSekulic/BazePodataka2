package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the SKorisnik database table.
 * 
 */
@Entity
@NamedQuery(name="SKorisnik.findAll", query="SELECT s FROM SKorisnik s")
public class SKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int korisnikId;

	private String korisnikIme;

	private String korisnikPrezime;

	//bi-directional many-to-one association to SPoruka
	@OneToMany(mappedBy="skorisnik1")
	private List<SPoruka> sporukas1;

	//bi-directional many-to-one association to SPoruka
	@OneToMany(mappedBy="skorisnik2")
	private List<SPoruka> sporukas2;

	public SKorisnik() {
		this.sporukas1 = new ArrayList<SPoruka>();
		this.sporukas2 = new ArrayList<SPoruka>();
	}
	
	public SKorisnik(String korisnikIme, String korisnikPrezime) {
		this();
		this.korisnikIme = korisnikIme;
		this.korisnikPrezime = korisnikPrezime;
	}

	public int getKorisnikId() {
		return this.korisnikId;
	}

	public void setKorisnikId(int korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getKorisnikIme() {
		return this.korisnikIme;
	}

	public void setKorisnikIme(String korisnikIme) {
		this.korisnikIme = korisnikIme;
	}

	public String getKorisnikPrezime() {
		return this.korisnikPrezime;
	}

	public void setKorisnikPrezime(String korisnikPrezime) {
		this.korisnikPrezime = korisnikPrezime;
	}

	public List<SPoruka> getSporukas1() {
		return this.sporukas1;
	}

	public void setSporukas1(List<SPoruka> sporukas1) {
		this.sporukas1 = sporukas1;
	}

	public SPoruka addSporukas1(SPoruka sporukas1) {
		getSporukas1().add(sporukas1);
		sporukas1.setSkorisnik1(this);

		return sporukas1;
	}

	public SPoruka removeSporukas1(SPoruka sporukas1) {
		getSporukas1().remove(sporukas1);
		sporukas1.setSkorisnik1(null);

		return sporukas1;
	}

	public List<SPoruka> getSporukas2() {
		return this.sporukas2;
	}

	public void setSporukas2(List<SPoruka> sporukas2) {
		this.sporukas2 = sporukas2;
	}

	public SPoruka addSporukas2(SPoruka sporukas2) {
		getSporukas2().add(sporukas2);
		sporukas2.setSkorisnik2(this);

		return sporukas2;
	}

	public SPoruka removeSporukas2(SPoruka sporukas2) {
		getSporukas2().remove(sporukas2);
		sporukas2.setSkorisnik2(null);

		return sporukas2;
	}

	@Override
	public String toString() {
		return korisnikIme + " " + korisnikPrezime;
	}
	
}