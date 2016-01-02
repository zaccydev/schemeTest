package scheme;

import matrice.GASM;
import formula.interval.FrequenceInterval;
import formula.interval.ListInterval;

public class TestMain {
	public static void main(String args[]) {
		GASM gasm = new GASM(70, 3);
		ListInterval lib = gasm.getSchemes();
		int max = 100;
		ListScheme schemes = new ListScheme(ETypeJeu.Keno);
		for (int i = 0; i < max; i++) {			
			System.out.println(schemes.get(i).toString());
		}
		FrequenceInterval fif = new FrequenceInterval(100);
		for (int i = 0; i < max; i++) {			
			System.out.println(lib.get(i).toString() + fif.getResults(lib.get(i), schemes));
		}
		
	}
}
