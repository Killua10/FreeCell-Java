import java.awt.Color;


public enum Enseigne {
	TREFLE(Color.BLACK),COEUR(Color.red), PIQUE(Color.black), CARREAU(Color.red);
	
	private final Color couleur;

Enseigne(Color couleur){
	this.couleur= couleur;
}

public  Color getCouleur(){
	return couleur; 
}
}
