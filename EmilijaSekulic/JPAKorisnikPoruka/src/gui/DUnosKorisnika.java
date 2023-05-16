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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class DUnosKorisnika extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIme;
	private JTextField tfPrezime;
	private KorisnikCrud kc = new KorisnikCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUnosKorisnika dialog = new DUnosKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DUnosKorisnika() {
		setTitle("Unos korisnika");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIme = new JLabel("Ime");
			lblIme.setHorizontalAlignment(SwingConstants.CENTER);
			lblIme.setBounds(40, 75, 88, 15);
			contentPanel.add(lblIme);
		}
		{
			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrezime.setBounds(40, 128, 70, 15);
			contentPanel.add(lblPrezime);
		}
		
		tfIme = new JTextField();
		tfIme.setBounds(159, 73, 215, 19);
		contentPanel.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(159, 126, 215, 19);
		contentPanel.add(tfPrezime);
		tfPrezime.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Unesi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String ime = tfIme.getText();
						String prezime = tfPrezime.getText();
						
						SKorisnik k = new SKorisnik(ime, prezime);
						boolean uspeh = kc.unesiKorisnika(k);
						
						if(uspeh) {
							
							JOptionPane.showMessageDialog(DUnosKorisnika.this, "Korisnik je uspesno unesen!", 
									"Uspeh :)", JOptionPane.INFORMATION_MESSAGE);
							
						} else {
							
							JOptionPane.showMessageDialog(DUnosKorisnika.this, "Korisnik nije uspesno unesen!", 
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
