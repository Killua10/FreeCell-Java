import javax.swing.JFrame;


public class UtilisationEnumeration {

	// bien comprendre la diff?rence avec les valeurs finales  static... 
	// le compilateur cr?e une classe, sous-classe de Enum qui est directement sous-classe de Object
	// pas de constructeur public...pas d'instanciation.
	// implicitement static
	// plus s?curitaire que les variables  final  static
	// Il existe une seule instance de chaque constante enum. 
	// on peut ?num?rer les ?l?ments dans l'ordre. 
	// les valeurs sont des objets. Elles peuvent donc ?tre utilis?es comme cl? dans une Map. 
	
	public static void main(String[] args) {
		 
		ValeurCartes val1= ValeurCartes.AS;
		ValeurCartes val2 = ValeurCartes.ROI;
		
		System.out.println (" val1= " + val1 + " val2 = "+ val2);
				
		// on peut l'utiliser dans un switch case..
		
		switch(val1){
		case  AS: System.out.println("as");
		break;
		case ROI: System.out.println("roi");
		break;	
		}
		
		// ?num?ration des ?l?ments dans l'ordre de d?finition. 
		for(ValeurCartes v : ValeurCartes.values()){
			System.out.println(" ordre: " + v.ordinal() + " "+  v);
		}

	}
}
