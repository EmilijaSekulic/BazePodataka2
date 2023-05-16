package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavniProzor {

	private JFrame frmPanelZaPoruke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor window = new GlavniProzor();
					window.frmPanelZaPoruke.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlavniProzor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanelZaPoruke = new JFrame();
		frmPanelZaPoruke.setTitle("Glavni prozor");
		frmPanelZaPoruke.setBounds(100, 100, 450, 300);
		frmPanelZaPoruke.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPanelZaPoruke.getContentPane().setLayout(null);
		
		JButton btnUnosKorisnika = new JButton("Unos korisnika");
		btnUnosKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DUnosKorisnika duk = new DUnosKorisnika();
				duk.setVisible(true);
			}
		});
		btnUnosKorisnika.setBounds(106, 30, 186, 25);
		frmPanelZaPoruke.getContentPane().add(btnUnosKorisnika);
		
		JButton btnUnosPoruke = new JButton("Unos poruke");
		btnUnosPoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DUnosPoruke dup = new DUnosPoruke();
				dup.setVisible(true);
			}
		});
		btnUnosPoruke.setBounds(106, 82, 186, 25);
		frmPanelZaPoruke.getContentPane().add(btnUnosPoruke);
		
		JButton btnBrisanjeKorisnika = new JButton("Obrisi korisnika");
		btnBrisanjeKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DBrisanjeKorisnika dbk = new DBrisanjeKorisnika();
				dbk.setVisible(true);
			}
		});
		btnBrisanjeKorisnika.setBounds(106, 138, 186, 25);
		frmPanelZaPoruke.getContentPane().add(btnBrisanjeKorisnika);
		
		JButton btnPrikazPoruka = new JButton("Prikazi poslate poruke");
		btnPrikazPoruka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DPrikaziPoruke dpp = new DPrikaziPoruke();
				dpp.setVisible(true);
			}
		});
		btnPrikazPoruka.setBounds(89, 187, 229, 25);
		frmPanelZaPoruke.getContentPane().add(btnPrikazPoruka);
	}
}
