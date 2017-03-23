import javax.swing.JFrame;


public class UtilisationEnumeration {

	// bien comprendre la différence avec les valeurs finales  static... 
	// le compilateur crée une classe, sous-classe de Enum qui est directement sous-classe de Object
	// pas de constructeur public...pas d'instanciation.
	// implicitement static
	// plus sécuritaire que les variables  final  static
	// Il existe une seule instance de chaque constante enum. 
	// on peut énumérer les éléments dans l'ordre. 
	// les valeurs sont des objets. Elles peuvent donc être utilisées comme clé dans une Map. 
	
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
		
		// énumération des éléments dans l'ordre de définition. 
		for(ValeurCartes v : ValeurCartes.values()){
			System.out.println(" ordre: " + v.ordinal() + " "+  v);
		}

	}
}
