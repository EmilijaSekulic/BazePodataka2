package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SPoruka database table.
 * 
 */
@Entity
@NamedQuery(name="SPoruka.findAll", query="SELECT s FROM SPoruka s")
public class SPoruka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int porukaId;

	private String datum;

	private String tekst;

	//bi-directional many-to-one association to SKorisnik
	@ManyToOne
	@JoinColumn(name="korisnikIdSalje")
	private SKorisnik skorisnik1;

	//bi-directional many-to-one association to SKorisnik
	@ManyToOne
	@JoinColumn(name="korisnikIdPrima")
	private SKorisnik skorisnik2;

	public SPoruka() {
	}

	public int getPorukaId() {
		return this.porukaId;
	}
	
	public SPoruka(String datum, String tekst) {
		this();
		this.datum = datum;
		this.tekst = tekst;
	}

	public void setPorukaId(int porukaId) {
		this.porukaId = porukaId;
	}

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public SKorisnik getSkorisnik1() {
		return this.skorisnik1;
	}

	public void setSkorisnik1(SKorisnik skorisnik1) {
		this.skorisnik1 = skorisnik1;
	}

	public SKorisnik getSkorisnik2() {
		return this.skorisnik2;
	}

	public void setSkorisnik2(SKorisnik skorisnik2) {
		this.skorisnik2 = skorisnik2;
	}

	@Override
	public String toString() {
		return getSkorisnik2() + " " + getDatum() + " " + getTekst();
	}
}