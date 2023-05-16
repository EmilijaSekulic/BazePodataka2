package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SPoruka;

public class TableModelPoruke extends AbstractTableModel {


	private static final long serialVersionUID = -2209252326294391701L;
	private List<SPoruka> poruke = null;
	
	private String[] kolone = {"Primalac", "Datum slanja", "Tekst"};
	
	public TableModelPoruke(List<SPoruka> poruke) {
		this.poruke = poruke;
	}

	@Override
	public int getColumnCount() {
	
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		
		return poruke.size();
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		SPoruka p = poruke.get(arg0);
		
		switch(arg1) {
		
		case 0:
			return p.getSkorisnik2().getKorisnikIme();
			
		case 1:
			return p.getDatum();
			
		case 2:
			return p.getTekst();
		}
		
		return null;
	}

	@Override
	public String getColumnName(int i) {
		// TODO Auto-generated method stub
		return kolone[i];
	}
}
