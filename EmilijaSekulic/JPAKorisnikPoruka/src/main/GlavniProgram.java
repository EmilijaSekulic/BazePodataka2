package main;

import java.util.List;

import crud.KorisnikCrud;
import crud.PorukaCrud;
import model.SKorisnik;
import model.SPoruka;

public class GlavniProgram {

	public static void main(String[] args) {

		KorisnikCrud kc = new KorisnikCrud();
		PorukaCrud pc = new PorukaCrud();
		
		/*
		SKorisnik k = new SKorisnik("Mika", "Mikic");
		boolean uspeh = kc.unesiKorisnika(k);
		System.out.println(uspeh);
		System.out.println(k.getKorisnikId());
		*/
		
		/*
		SPoruka p = new SPoruka("12.03.2022.", "Novi dan");
		boolean uspeh = pc.unesiPoruku(p);
		System.out.println(uspeh);
		 */
		
		/*
		SKorisnik k = kc.pronadjiKorisnika(14);
		//System.out.println(k);
		boolean uspeh = kc.izbrisiKorisnika(k);
		System.out.println(uspeh);
		*/
		
		SKorisnik k = kc.pronadjiKorisnika(1);
		List<SPoruka> poruke = pc.listaPorukaKorisnika(k);
		for(SPoruka p : poruke) {
			System.out.println(p);
		}
		
		System.exit(0);
	}
}
