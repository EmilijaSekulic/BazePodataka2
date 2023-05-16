package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.KorisnikCrud;
import crud.PorukaCrud;
import model.SKorisnik;
import model.SPoruka;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DPrikaziPoruke extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<SKorisnik> cbKorisnici;
	private KorisnikCrud kc = new KorisnikCrud();
	private JTable table;
	private PorukaCrud pc = new PorukaCrud();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DPrikaziPoruke dialog = new DPrikaziPoruke();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DPrikaziPoruke() {
		setTitle("Prikaz poslatih poruka");
		setBounds(100, 100, 666, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblKorisnik = new JLabel("Izaberite korisnika:");
				lblKorisnik.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblKorisnik);
			}
			
				cbKorisnici = new JComboBox<SKorisnik>();
				panel.add(cbKorisnici);
				
				List<SKorisnik> korisnici = kc.listaKorisnika();
				for(SKorisnik k : korisnici) {
					cbKorisnici.addItem(k);
				
			}
			{
				JButton btnPrikazi = new JButton("Prikazi");
				btnPrikazi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						SKorisnik k = (SKorisnik) cbKorisnici.getSelectedItem();
						List<SPoruka> poruke = pc.listaPorukaKorisnika(k);
						
						TableModelPoruke tmp = new TableModelPoruke(poruke);
						table.setModel(tmp);
					}
				});
				panel.add(btnPrikazi);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
