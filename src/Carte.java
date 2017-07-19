import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class Carte {

	public  static final int LARGEUR_CARTE;  //
    public  static final int HAUTEUR_CARTE;
    private static final ImageIcon ImageCarteBidon;

	private ImageIcon image;
	private int positionX;
	private int positionY;
	private Enseigne enseigne;
	private int intValeur;

	public Enseigne getEnseigne() {
		return enseigne;
	}

	public void setEnseigne(Enseigne enseigne) {
		this.enseigne = enseigne;
	}

	public static int getLargeurCarte() {
		return LARGEUR_CARTE;
	}

	public static int getHauteurCarte() {
		return HAUTEUR_CARTE;
	}

	public static ImageIcon getImagecartebidon() {
		return ImageCarteBidon;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public void setIntValeur(int intValeur) {
		this.intValeur = intValeur;
	}

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	Rectangle rec;

	static {
		ImageCarteBidon = new ImageIcon("resources\\img\\carreau1.gif");
		LARGEUR_CARTE= ImageCarteBidon.getIconWidth();
		HAUTEUR_CARTE= ImageCarteBidon.getIconHeight();
	}

	Carte(int positionX, int positionY, ImageIcon image) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.image = image;
		rec= new Rectangle(positionX,positionY,ImageCarteBidon.getIconWidth(),ImageCarteBidon.getIconHeight());

	}

	Carte(int intValeur, Enseigne enseigne,int positionX, int positionY, ImageIcon image) {
		// TODO Auto-generated constructor stub
		this.positionX = positionX;
		this.positionY = positionY;
		this.enseigne = enseigne;
		this.intValeur = intValeur;
		this.image = image;
		rec= new Rectangle(positionX,positionY,ImageCarteBidon.getIconWidth(),ImageCarteBidon.getIconHeight());
	}

	public  void setPositionY(int y){
		this.positionY = y;
		rec = new Rectangle(positionX,positionY,ImageCarteBidon.getIconWidth(),ImageCarteBidon.getIconHeight());
	}


	public int getIntValeur() {
		return intValeur;
	}

	public  void setPositionX(int x){
		this.positionX = x;
		rec= new Rectangle(positionX,positionY,ImageCarteBidon.getIconWidth(),ImageCarteBidon.getIconHeight());
	}

	public int getXPositionX(){
		return positionX;
	}

	public int getPositionY(){
		return positionY;
	}

	public boolean estDansCarte(int x , int y ) {
		return (x >= positionX && x < positionX+LARGEUR_CARTE) && (y >= positionY && y < positionY+HAUTEUR_CARTE);
	}

	public void dessiner (Graphics g){

		image.paintIcon(null,g, positionX, positionY);

	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImageIcon( ImageIcon img) {
		this.image= img;
	}

	Rectangle getRec(){
		return rec;
	}
	}


