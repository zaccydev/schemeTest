package scheme;

import perslib.Timer;
import formula.interval.FrequenceInterval;
import formula.interval.ListInterval;
import formula.interval.ListIntervalFormula;
import matrice.GASM;

public class Main {
	
	private static final int nbResult = 100;  
	
	public static void main(String args[]) {
		GASM gasm = new GASM(70, 3);
		ListInterval lib = gasm.getSchemes();
		ListIntervalFormula lif = new ListIntervalFormula();
		FrequenceInterval ffi = new FrequenceInterval(nbResult);
		ffi.setSortingOrderDesc();
		lif.add(ffi);
		
		//parse les csv de résultat (2 fichiers au total)
		Timer timer = new Timer();		
		ListScheme schemes = new ListScheme(ETypeJeu.Keno);
				
		System.out.println("Nombre de tirages parsés depuis le csv: " + schemes.size()); 
		System.out.println("Temps pour parser le csv de données : " + timer.startSince() + "s");
		System.out.println("Nombre de combinaisons à analyser : " + (nbResult * lib.size()));
		timer.reinit();
		
		//calcul des statistiques et tri des résultats 
		lif.setStats(lib, schemes);
		lib.sortBy(lif);
		System.out.println("Temps de l'analyse statistique et du tri des résultats : " + timer.startSince() + "s");
		for (int i = 0; i < 3; i++) {
			System.out.println(lib.get(i).toString() + " -- freq: " + (int) lib.get(i).getStat(ffi));			
		}
	}

}
