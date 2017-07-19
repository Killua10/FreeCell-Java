
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanneauCarte extends JPanel implements MouseListener, MouseMotionListener {

	Carte cCarte;
	private Fenetre fenetreJeu;
	int deplacementX;
	int deplacementY;
	int nouveauX;
	int nouveauY;
	boolean carteSelectionnee;
	boolean rentreDansRectSymbole = false;
	//boolean blnStackEstVide = false;

	// Tableau Rectangle FreeCell
	Rectangle tabRectFreeCell[] = new Rectangle[4];

	// Tableau Rectangle Symbole
	Rectangle tabRectSymbole[] = new Rectangle[4];

	// ArrayList qui contient les cartes Symbole
	ArrayList<Carte> alCarteSymbole = new ArrayList<Carte>();

	// Tableau Rectangle destiner au Stacks(Piles)
	Rectangle tabRectVide[] = new Rectangle[8];

	// ArrayList qui contient toutes les cartes
	ArrayList<Carte> al52Carte = new ArrayList<Carte>();

	// Tableau des Stacks
	Stack<Carte>[] tabStack = new Stack[8];

	// Tableau des Stacks pour les As
	Stack<Carte>[] tabStackAs = new Stack[4];

	// Variable de position Initial
	int posXInitial;
	int posYInitial;

	int indexPile;

	// tableau contenant les carte placer soit dans les cases libres et les case As
	Carte tabCarteFreeCell[] = new Carte[4];
	Carte tabCarteRectSymbole[] = new Carte[4];

	int espace = 30;
	int intValeurXrecDefaut = 200;
	int intValeurYrecDefaut = 20;
	int intCompteur = 1;

	private static final long serialVersionUID = 1L;

	PanneauCarte(Fenetre fenetreJeu) {
		this.fenetreJeu = fenetreJeu;
		setBackground(Color.CYAN.darker());
		int posX = -15;
		int posY = 153;

		for (int i = 0; i < tabStackAs.length; i++) {
			tabStackAs[i] = new Stack<Carte>();
		}

		// Initialisation des Rectangle FreeCell
		for (int i = 0; i < tabRectFreeCell.length; i++) {
			if (i == 0) {
				tabRectFreeCell[i] = new Rectangle(intValeurXrecDefaut, intValeurYrecDefaut, Carte.LARGEUR_CARTE - 1,
						Carte.HAUTEUR_CARTE - 1);
			} else {
				tabRectFreeCell[i] = new Rectangle((int) tabRectFreeCell[i - 1].getMaxX() + intValeurYrecDefaut,
						intValeurYrecDefaut, Carte.LARGEUR_CARTE - 1, Carte.HAUTEUR_CARTE - 1);
				if (i == tabRectFreeCell.length - 1) {
					tabRectSymbole[0] = new Rectangle((int) tabRectFreeCell[i].getMaxX() + 100, intValeurYrecDefaut,
							Carte.LARGEUR_CARTE - 1, Carte.HAUTEUR_CARTE - 1);
				}
			}
		}

		// Initialisation des Rectangle Symbole
		for (int i = 1; i < tabRectSymbole.length; i++) {
			System.out.println(i);
			tabRectSymbole[i] = new Rectangle((int) tabRectSymbole[i - 1].getMaxX() + intValeurYrecDefaut,
					intValeurYrecDefaut, Carte.LARGEUR_CARTE - 1, Carte.HAUTEUR_CARTE - 1);

		}

		// Initialisation des Rectangle de Stack Vide
		for (int i = 0; i < tabRectVide.length; i++) {
			tabRectVide[i] = new Rectangle(espace + intValeurXrecDefaut, Carte.LARGEUR_CARTE * 2,
					Carte.LARGEUR_CARTE - 1, Carte.HAUTEUR_CARTE - 1);
			espace += intValeurYrecDefaut + Carte.LARGEUR_CARTE;
		}

		// Creation de toutes les cartes
		for (ValeurCartes val : ValeurCartes.values()) {
			for (Enseigne ens : Enseigne.values()) {
				cCarte = new Carte(val.ordinal() + 1, ens, posX, posY,
						new ImageIcon("img\\" + ens.toString().toLowerCase() + (val.ordinal() + 1) + ".gif"));
				al52Carte.add(cCarte);
				cCarte = null;
				posX = posX + 20;

			}
		}

		// Creation des cartes Symbole
		int intCompteurIndexRectSymbole = 0;
		for (Enseigne ens : Enseigne.values()) {
			// TODO: make sure 0 works
			cCarte = new Carte(0, ens, (int) tabRectSymbole[intCompteurIndexRectSymbole].getX(),
					(int) tabRectSymbole[intCompteurIndexRectSymbole].getY(),
					new ImageIcon("img\\" + ens.toString().toLowerCase() + ".gif"));
			alCarteSymbole.add(cCarte);
			cCarte = null;
			// posX = posX + 20;
			intCompteurIndexRectSymbole++;
		}

		// Shuffle
		Collections.shuffle(al52Carte);

		// Initialisation et remplissage des stacks
		for (int i = 0, n = 0; i < tabStack.length; i++) {
			tabStack[i] = new Stack<Carte>();
			posX += espace + Carte.LARGEUR_CARTE;
			for (int j = 0, espace = 0; j < (i < 4 ? 7 : 6); j++, n++) {
				Carte cTemp = al52Carte.get(n);
				cTemp.setPositionX((int) tabRectVide[i].getX());
				cTemp.setPositionY((int) tabRectVide[i].getY() + espace);
				tabStack[i].push(cTemp);
				espace += 30;
			}
		}

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// System.out.println("paint");

		// Dessiner les 4 rectangle FreeCell(Libre)
		for (Rectangle Rec : tabRectFreeCell) {
			dessinerRect(g, Rec);
		}

		// Dessiner les 8 rectangle vide destiner au Stacks(Piles)
		for (Rectangle Rec : tabRectVide) {
			dessinerRect(g, Rec);
		}

		// Dessiner les 4 rectangle vide Symbole
		for (Rectangle rec : tabRectSymbole) {
			dessinerRect(g, rec);
		}

		// Dessiner les 4 Carte Symbole
		for (Carte carte : alCarteSymbole) {
			carte.dessiner(g);
		}

		// Dessiner les Stacks(Piles)
		for (Stack<Carte> stack : tabStack) {
			for (Carte carte : stack) {
				carte.dessiner(g);
			}
		}

		// Dessiner les Cartes deposer dans les Rectangle FreeCell(Libre)
		for (Carte cTemp : tabCarteFreeCell) {
			if (cTemp != null) {
				cTemp.dessiner(g);
			}
		}

		// Dessiner les Cartes deposer dans les Rectangle Symbole
		for (Carte cTemp : tabCarteRectSymbole) {
			if (cTemp != null) {
				cTemp.dessiner(g);
			}
		}

		if (carteSelectionnee) {
			cCarte.dessiner(g);
		}
	}

	public void dessinerRect(Graphics g, Rectangle Rec) {

		g.setColor(Color.CYAN.darker().darker().darker());
		g.fillRoundRect((int) Rec.getX(), (int) Rec.getY(), (int) Rec.getWidth(), (int) Rec.getHeight(), 10, 10);
		g.drawRoundRect((int) Rec.getX(), (int) Rec.getY(), (int) Rec.getWidth(), (int) Rec.getHeight(), 15, 15);

	}

	public void dessinerImg(Graphics g, ImageIcon img, Rectangle Rec) {

		g.drawImage(img.getImage(), (int) Rec.getX(), (int) Rec.getY(), null);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("Dragged");
		if (carteSelectionnee) {
			nouveauX = e.getX() - deplacementX;
			nouveauY = e.getY() - deplacementY;

			// on s'assure de ne pas envoyer la carte hors de la fenetre.
			nouveauX = Math.max(nouveauX, 0);
			nouveauX = Math.min(nouveauX, getWidth() - cCarte.getImage().getIconWidth());
			nouveauY = Math.max(nouveauY, 0);
			nouveauY = Math.min(nouveauY, getHeight() - cCarte.getImage().getIconHeight());

			// nouvelles positions de la carte.
			cCarte.setPositionX(nouveauX);
			cCarte.setPositionY(nouveauY);

			repaint(); // car la position a change.

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
		// position actuelle de la souris
		int x = e.getX();
		int y = e.getY();

		// on verifie si la souris sur la carte

		// if(c.estDansCarte(x,y)== true)
		for (int i = 0; i < tabStack.length && carteSelectionnee == false; i++) {
			if (!tabStack[i].isEmpty()) {

				if (tabStack[i].peek().getRec().contains(x, y)) {
					cCarte = tabStack[i].peek();
					posXInitial = cCarte.getXPositionX();
					posYInitial = cCarte.getPositionY();
					carteSelectionnee = true;
					indexPile = i;
					System.out.println("Index Pile: " + indexPile);
					System.out.println(" pressed: " + cCarte.getIntValeur());
					System.out.println(" pressed: " + cCarte.getEnseigne());
				}
			}
		}

		for (int i = 0; i < tabCarteFreeCell.length && carteSelectionnee == false; i++) {
			if (tabCarteFreeCell[i] != null) {

				if (tabCarteFreeCell[i].getRec().contains(x, y)) {
					cCarte = tabCarteFreeCell[i];
					posXInitial = cCarte.getXPositionX();
					posYInitial = cCarte.getPositionY();
					carteSelectionnee = true;
				}
			}
		}

		for (int i = 0; i < tabCarteRectSymbole.length; i++) {
			if (tabCarteRectSymbole[i] != null) {

				if (tabCarteRectSymbole[i].getRec().contains(x, y)) {
					rentreDansRectSymbole = true;
					// carteSelectionnee = false;
				}
			}
		}

		if (cCarte != null && rentreDansRectSymbole == false) {

			if (cCarte.getRec().contains(x, y)) {
				carteSelectionnee = true;

				deplacementX = x - cCarte.getXPositionX();
				deplacementY = y - cCarte.getPositionY();
			}
		}

	}

	public void verificationIndexPile(){
		if (indexPile != -1) {
			tabStack[indexPile].pop();
		} else {
			for (int j = 0; j < tabCarteFreeCell.length; j++) {
				if (tabCarteFreeCell[j] == cCarte) {
					tabCarteFreeCell[j] = null;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
		int x = e.getX();
		int y = e.getY();

		boolean blnValide = false;

		if (carteSelectionnee) {
			for (int i = 0; i < tabRectFreeCell.length; i++) {
				if (tabRectFreeCell[i].contains(x, y)) {
					blnValide = true;
					// System.out.println("contains");
					if (tabCarteFreeCell[i] == null) {
						verificationIndexPile();

						cCarte.setPositionX((int) tabRectFreeCell[i].getX());
						cCarte.setPositionY((int) tabRectFreeCell[i].getY());
						if (cCarte != null) {
							tabCarteFreeCell[i] = cCarte;
						}
						posXInitial = cCarte.getXPositionX();
						posYInitial = cCarte.getPositionY();

					} else {
						cCarte.setPositionX(posXInitial);
						cCarte.setPositionY(posYInitial);
					}

				} else if (tabRectSymbole[i].contains(x, y)) {
						if (tabCarteRectSymbole[i] == null && cCarte.getIntValeur() == 1
								&& cCarte.getEnseigne() == alCarteSymbole.get(i).getEnseigne()) {
							blnValide = true;

							tabStackAs[i].push(cCarte);
							System.out.println(tabStackAs[i].push(cCarte));
							cCarte.setPositionX((int) tabRectSymbole[i].getX());
							cCarte.setPositionY((int) tabRectSymbole[i].getY());
							tabCarteRectSymbole[i] = cCarte;

							verificationIndexPile();

						} else if ((cCarte.getEnseigne() == alCarteSymbole.get(i).getEnseigne()) && !(tabStackAs[i].isEmpty())) {

							if ((tabStackAs[i].peek().getIntValeur() + 1) == (cCarte.getIntValeur())) {
							blnValide = true;

							tabStackAs[i].push(cCarte);

							cCarte.setPositionX((int) tabRectSymbole[i].getX());
							cCarte.setPositionY((int) tabRectSymbole[i].getY());

							tabCarteRectSymbole[i] = cCarte;

							verificationIndexPile();
						}
						}

						// Verification de la victoire
						if (tabStackAs[0].size() == 14 && tabStackAs[1].size() == 14 && tabStackAs[2].size() == 14
								&& tabStackAs[3].size() == 14) {
							int recommencer = JOptionPane.showConfirmDialog(null,
									"Felicitation vous avez gagnier!\nVoulez-vous recommencere", "Recommencer",
									JOptionPane.YES_NO_OPTION);

							if (recommencer == JOptionPane.YES_OPTION) {
								fenetreJeu.nouvellePartie();
							}
							else {
								System.exit(0);
							}
						}

				} else {
					for (int j = 0; j < tabStack.length; j++) {
						if (!tabStack[j].isEmpty()) {

							if (j != indexPile) {
								if (tabStack[j].lastElement().estDansCarte(x, y)) {
									if (!cCarte.getEnseigne().getCouleur().equals(tabStack[j].lastElement().getEnseigne().getCouleur())) {
										if (cCarte.getIntValeur() + 1 == tabStack[j].lastElement().getIntValeur()) {
											cCarte.setPositionX(tabStack[j].lastElement().getPositionX());
											cCarte.setPositionY(
													tabStack[j].lastElement().getPositionY() + intValeurYrecDefaut);
											tabStack[j].push(cCarte);
											posXInitial = cCarte.getXPositionX();
											posYInitial = cCarte.getPositionY();

											verificationIndexPile();
										}
									}
								}
							}
						}
					}
				}

				if (!blnValide) {
					cCarte.setPositionX(posXInitial);
					cCarte.setPositionY(posYInitial);
				}
			}
			for (int j = 0; j < tabStack.length; j++) {
				if (tabRectVide[j].contains(x, y)) {
					if (tabStack[j].isEmpty()) {

						cCarte.setPositionX((int) tabRectVide[j].getX());
						cCarte.setPositionY((int) tabRectVide[j].getY());
						tabStack[j].push(cCarte);
						posXInitial = cCarte.getXPositionX();
						posYInitial = cCarte.getPositionY();

						verificationIndexPile();

					} else {
						cCarte.setPositionX(posXInitial);
						cCarte.setPositionY(posYInitial);
					}
				}
			}
			partieBloquer();
			repaint();
		}
		indexPile = -1;
		carteSelectionnee = false;
	}

	public void partieBloquer() {
		boolean blnPartieBloquer = false;
		boolean blnFreeCellVide = false;
		boolean blnStackVide = false;

		// Verification de si une des stack et vide
		for (Stack<Carte> stack : tabStack) {
			if (stack.isEmpty()) {
				blnStackVide = true;
			}
		}

		// Verification de si une des FreeCell et vide
		for (Carte carte : tabCarteFreeCell) {
			if (carte == null) {
				blnFreeCellVide = true;
			}
		}

		//System.out.println("Stack Vide: " + blnStackVide);
		//System.out.println("FreeCell Vide: " + blnFreeCellVide);
		if (!blnStackVide && !blnFreeCellVide) {
			blnPartieBloquer = true;

			// Verification des stacks
			for (Stack<Carte> stackCarte : tabStack) {
				for (Stack<Carte> stackCarte2 : tabStack) {
					if (stackCarte.peek().getEnseigne().getCouleur() != stackCarte2.peek().getEnseigne().getCouleur() && stackCarte.peek().getIntValeur() - 1 == stackCarte2.peek().getIntValeur()) {
						System.out.println("Stack <----> Srack Une carte d'un stack peut etre mis dans un autre stack");
						blnPartieBloquer = false;
					}
				}
			}

			// Verification des FreeCell avec les As
			for (Carte carte : tabCarteFreeCell) {
				for (Stack<Carte> stackAs : tabStackAs) {
					if (!stackAs.isEmpty()) {

						if (carte.getIntValeur() - 1 == stackAs.peek().getIntValeur()
								&& carte.getEnseigne().getCouleur() == stackAs.peek().getEnseigne().getCouleur()) {
							System.out.println("As <----> FreeCell Une Carte FreeCell peut etre mis dans les As");
							blnPartieBloquer = false;
						}
					}
				}
			}

			// Verification des Stack avec As
			for (Stack<Carte> stackCarte : tabStack) {
				for (Stack<Carte> stackAs : tabStackAs) {
					if (!stackAs.isEmpty()) {
						if (stackCarte.peek().getIntValeur() - 1 == stackAs.peek().getIntValeur() && stackCarte.peek().getEnseigne() == stackAs.peek().getEnseigne()) {
							System.out.println("Stack <----> As Une carte des stack peut etre mis dans les As");
							blnPartieBloquer = false;
						}
					}
				}
			}

			// Verification des FreeCell avec Stack
			for (Carte carte : tabCarteFreeCell) {
				for (Stack<Carte> stackCarte : tabStack) {
					if (stackCarte.peek().getIntValeur() -1 == carte.getIntValeur() && carte.getEnseigne().getCouleur() != stackCarte.peek().getEnseigne().getCouleur() ) {
						System.out.println("FreeCell <----> Stack Une carte des FreeCell peut etre mis dans un stack");
						blnPartieBloquer = false;
					}
				}
			}
		}

		System.out.println("boolean partie bloquer: " + blnPartieBloquer);
		if (blnPartieBloquer) {
			int recommencer = JOptionPane.showConfirmDialog(null,
					"Aucun mouvement possible,vous etes bloquer!\nVoulez-vous recommencere", "Recommencer",
					JOptionPane.YES_NO_OPTION);

			if (recommencer == JOptionPane.YES_OPTION) {
				fenetreJeu.nouvellePartie();
			}
			else {
				System.exit(0);
			}
		}
	}

	// evenement Ignores
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
}
