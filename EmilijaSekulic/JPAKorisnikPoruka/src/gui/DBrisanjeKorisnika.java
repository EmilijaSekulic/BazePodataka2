package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.KorisnikCrud;
import model.SKorisnik;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class DBrisanjeKorisnika extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<SKorisnik> cbKorisnici;
	private KorisnikCrud kc = new KorisnikCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DBrisanjeKorisnika dialog = new DBrisanjeKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DBrisanjeKorisnika() {
		setTitle("Brisanje korisnika");
		setBounds(100, 100, 659, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblKorisnik = new JLabel("Izaberite korisnika:");
			lblKorisnik.setHorizontalAlignment(SwingConstants.CENTER);
			lblKorisnik.setBounds(47, 71, 163, 15);
			contentPanel.add(lblKorisnik);
		}
		{
			cbKorisnici = new JComboBox<SKorisnik>();
			cbKorisnici.setBounds(249, 62, 363, 24);
			contentPanel.add(cbKorisnici);
			
			List<SKorisnik> korisnici = kc.listaKorisnika();
			for(SKorisnik k : korisnici) {
				cbKorisnici.addItem(k);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Obrisi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						SKorisnik k = (SKorisnik) cbKorisnici.getSelectedItem();
						boolean uspeh = kc.izbrisiKorisnika(k);
						
						if(uspeh) {
							
							JOptionPane.showMessageDialog(DBrisanjeKorisnika.this, "Korisnik je uspesno izbrisan!", 
									"Uspeh :)", JOptionPane.INFORMATION_MESSAGE);
							
						} else {
							
							JOptionPane.showMessageDialog(DBrisanjeKorisnika.this, "Korisnik nije uspesno izbrisan!", 
									"Neuspeh :(", JOptionPane.ERROR_MESSAGE);
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Zatvori");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
