package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.KorisnikCrud;
import model.SKorisnik;
import model.SPoruka;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class DUnosPoruke extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDatum;
	private KorisnikCrud kc = new KorisnikCrud();
	private JComboBox<SKorisnik> cbPosiljaoci;
	private JComboBox<SKorisnik> cbPrimaoci;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUnosPoruke dialog = new DUnosPoruke();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DUnosPoruke() {
		setTitle("Unos poruke");
		setBounds(100, 100, 607, 409);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPosiljaoc = new JLabel("Salje");
			lblPosiljaoc.setHorizontalAlignment(SwingConstants.CENTER);
			lblPosiljaoc.setBounds(40, 45, 107, 15);
			contentPanel.add(lblPosiljaoc);
		}
		{
			JLabel lblPrimaoc = new JLabel("Prima");
			lblPrimaoc.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrimaoc.setBounds(40, 105, 107, 15);
			contentPanel.add(lblPrimaoc);
		}
		{
			JLabel lblDatum = new JLabel("Datum");
			lblDatum.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatum.setBounds(40, 165, 107, 15);
			contentPanel.add(lblDatum);
		}
		{
			JLabel lblTekst = new JLabel("Tekst");
			lblTekst.setHorizontalAlignment(SwingConstants.CENTER);
			lblTekst.setBounds(50, 225, 97, 15);
			contentPanel.add(lblTekst);
		}
		
		cbPosiljaoci = new JComboBox<SKorisnik>();
		cbPosiljaoci.setBounds(208, 36, 346, 24);
		contentPanel.add(cbPosiljaoci);
		
		List<SKorisnik> posiljaoci = kc.listaKorisnika();
		for(SKorisnik k1 : posiljaoci) {
			cbPosiljaoci.addItem(k1);
		}
		
		cbPrimaoci = new JComboBox<SKorisnik>();
		cbPrimaoci.setBounds(208, 100, 346, 24);
		contentPanel.add(cbPrimaoci);
		
		List<SKorisnik> primaoci = kc.listaKorisnika();
		for(SKorisnik k2 : primaoci) {
			cbPrimaoci.addItem(k2);
		}
		
		tfDatum = new JTextField();
		tfDatum.setBounds(208, 163, 143, 19);
		contentPanel.add(tfDatum);
		tfDatum.setColumns(10);
		
		JTextArea taTekst = new JTextArea();
		taTekst.setBounds(208, 225, 315, 81);
		contentPanel.add(taTekst);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Unesi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						SKorisnik posiljaoc = (SKorisnik) cbPosiljaoci.getSelectedItem();
						SKorisnik primalac = (SKorisnik) cbPrimaoci.getSelectedItem();
						
						String datum = tfDatum.getText();
						String tekst = taTekst.getText();
						
						SPoruka p = new SPoruka(datum, tekst);
						p.setSkorisnik1(posiljaoc);
						p.setSkorisnik2(primalac);
						
						boolean uspeh = kc.poveziPorukuIKorisnika(posiljaoc, primalac, p);
						
						if(uspeh) {
							
							JOptionPane.showMessageDialog(DUnosPoruke.this, "Povezivanje korisnika i poruke je bilo uspesno!", 
									"Uspeh :)", JOptionPane.INFORMATION_MESSAGE);
							
						} else {
							
							JOptionPane.showMessageDialog(DUnosPoruke.this, "Povezivanje korisnika i poruke nije bilo uspesno!", 
									"Neuspeh :)", JOptionPane.ERROR_MESSAGE);
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
