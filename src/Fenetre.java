import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fenetre extends JFrame{

	private JMenu mPartie;
	private JMenu mAide;
	private JMenuItem miApropos;
	private JMenuItem miNouvelPartie;
	private JMenuItem miQuitter;
	private JMenuBar menuBar;
	private JPanel panelBtn;
	private JButton btnNouvelPartie;
	private JButton btnQuitter;
	PanneauCarte  pCarte  = new PanneauCarte(this);

	Fenetre() {
		setTitle("FreeCell - Mohamed Cherifi");
		//setBackground(Color.PINK);

		//Menu Partie
		mPartie = new JMenu("Partie");
		mPartie.setMnemonic('P');

			//Nouvelle Partie
			miNouvelPartie = new JMenuItem("Nouvelle Partie");
			miNouvelPartie.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					nouvellePartie();
				}
			});

			//Quitter
			miQuitter = new JMenuItem("Quitter");
			miQuitter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int intComframtion = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter?","Quitter",JOptionPane.YES_NO_OPTION);
					if (intComframtion == JOptionPane.YES_OPTION) {
						System.exit(0);
					}

				}
			});

		//Menu Aide
		mAide = new JMenu("Aide");
		mAide.setMnemonic('A');


			//A Propos
			miApropos = new JMenuItem("A propos");
			miApropos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String strAuteur = "<html><b><u>FreeCell/Solitaire</u></b></html>\nAuteur: Mohamed Cherifi. \nDate: Septembre 2016.\n\n<html><b><u>Comment jouer:</u></b></html>\n";
					String strRegles = "<html><ul>Tirez des cartes au bas de chaque colonne et dêplacez-les comme suit:<br><br> <li>Dêune colonne ê une cellule libre.</li><li>Une seule carte peut occuper chaque cellule libre ê la fois.</li> <li>Dêune colonne ê une autre colonne (ou dêune cellule libre ê une colonne).</li><li>Les cartes doivent être placêes dans une colonne dans lêordre<br> dêcroissant en alternant les couleurs (rouge et noir).</li> <li>Dêune colonne ê une cellule de destination.</li><li>Chaque pile doit comporter une seule couleur et commencer par un as.</li></ul></html>";
					JOptionPane.showMessageDialog(null, strAuteur + strRegles);
				}
			});

		//Ajout Menu Partie
		mPartie.add(miNouvelPartie);
		mPartie.addSeparator();
		mPartie.add(miQuitter);

		//Ajout Menu Aide
		mAide.add(miApropos);

		//Ajout MenuBar
		menuBar = new JMenuBar();
		menuBar.add(mPartie);
		menuBar.add(mAide);

		btnNouvelPartie = new JButton("Nouvelle Partie");
		btnNouvelPartie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				miNouvelPartie.doClick();
			}
		});

		btnQuitter  = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				miQuitter.doClick();
			}
		});

		panelBtn = new JPanel();
		panelBtn.setBackground(Color.cyan.darker().darker().darker());
		panelBtn.setLayout(new FlowLayout());
		panelBtn.add(btnNouvelPartie);
		panelBtn.add(btnQuitter);

		//JLabel background = new JLabel(new ImageIcon("Aces.jpg"));
		//background.add(menuBar,BorderLayout.NORTH);

		add(menuBar,BorderLayout.NORTH);
		add(panelBtn,BorderLayout.SOUTH);
		//add(background);
		add(pCarte);

		setSize(1250, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void nouvellePartie() {
		remove(pCarte);
		pCarte = new PanneauCarte(this);
		add(pCarte);
		validate();
		repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fenetre f = new Fenetre();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
