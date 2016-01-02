package formula.interval;

import java.util.ArrayList;
import java.util.Iterator;
import scheme.ListScheme;
import scheme.Interval;


public class ListIntervalFormula implements Iterable<IIntervalFormula> {
	private ArrayList<IIntervalFormula> listFormula;
	
	public ListIntervalFormula() {
		listFormula = new ArrayList<IIntervalFormula>();
	}
	
	public Iterator<IIntervalFormula> iterator() {
		return listFormula.iterator();
	}
	
	private int distance = 0;
	
	public void setDistance(int distance) {
		this.distance = distance;
		
		for (IIntervalFormula f : listFormula) {
			f.setDistance(distance);
		}
	}
	
	public int getDistance() {
		return distance;
	}
	/**
	 * Ajouter une formule (en fin de liste).
	 * @param fml
	 */
	public void add(IIntervalFormula fml) {
		fml.setDistance(distance);
		listFormula.add(fml);		
	}
	
	/**
	 * Ajoute la formule en début de liste.
	 * @param fml
	 */
	public void unshift(IIntervalFormula fml) {
		fml.setDistance(distance);
		listFormula.add(0, fml);		
	}

	public int size() {
		return listFormula.size();
	}

	public IIntervalFormula get(int idx) {
		return listFormula.get(idx);
	}
	
	public void setStats(ListInterval listInterval, ListScheme schemes) {
			
		for (Interval intv : listInterval)
			for (IIntervalFormula iif : listFormula )
				intv.setStat(iif, schemes);
	}

}
