import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterfaceFreeCell extends JFrame{
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceFreeCell exe = new InterfaceFreeCell();
	}
	
	private JMenu mPartie;
	private JMenu mAide;
	private JMenuItem miApropos;
	private JMenuItem miNouvelPartie;
	private JMenuItem miQuitter;
	private JMenuBar menuBar;
	private JPanel panelCarte;
	
	public InterfaceFreeCell(){
		setTitle("FreeCell - Mohamed Cherifi");
		setBackground(Color.PINK);
		
		//Menu Partie
		mPartie = new JMenu("Partie");
		mPartie.setMnemonic('P');
		
			//Nouvelle Partie
			miNouvelPartie = new JMenuItem("Nouvelle Partie");
			
			//Quitter
			miQuitter = new JMenuItem("Quitter");
		
		//Menu Aide
		mAide = new JMenu("Aide");
		mAide.setMnemonic('A');
		
			//A Propos
			miApropos = new JMenuItem("A propos");
			miApropos.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, "Auteur: Mohamed Cherifi\n Real Madrid ");
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
		
		JLabel background = new JLabel(new ImageIcon("Aces.jpg"));
		background.setOpaque(true);
		//background.add(menuBar,BorderLayout.NORTH);
		
		add(menuBar,BorderLayout.NORTH);
		add(background);
		
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
