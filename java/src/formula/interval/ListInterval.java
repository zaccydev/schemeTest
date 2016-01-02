package formula.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import scheme.Interval;


public class ListInterval implements Iterable<Interval>{
	
	protected ArrayList<Interval> listInterval;
	
	public Iterator<Interval> iterator() {
		return listInterval.iterator();
	}
	
	public ListInterval() {
		listInterval = new ArrayList<Interval>();
	}
	
	public ListInterval(ArrayList<Interval> listInterval) {
		this.listInterval = listInterval;
	}
	
	public void add(Interval intv) {
		listInterval.add(intv);
	}
	
	public int size() {
		return listInterval.size();
	}
	
	public void remove (int index) {
		listInterval.remove(index);
	}

	public Interval get(int index) {
		return listInterval.get(index);
	}
	
	public void sortBy(ListIntervalFormula lif) {

		for (Interval intv : listInterval) {
			intv.setComparatorsIIF(lif);			
		}
		Collections.sort(listInterval);
	}
	
	public ListInterval getSortedInterval(int nbIntv, ListIntervalFormula lif ) {		
		ListInterval il = new ListInterval();
		
		this.sortBy(lif);

		for (int i = 0; i < nbIntv; i++) {	
			if (i < listInterval.size())
				il.add(listInterval.get(i));
		}
		return il;
	}
	
	public void removeIfStatNotEqual(IIntervalFormula intvF, double value) {
		ArrayList<Interval> newList = new ArrayList<Interval>();
		
		for (Interval intV : this.listInterval) {	
			if (intV.getStat(intvF) == value) {
				newList.add(intV);
			}
		}
		this.listInterval = newList;
	}
	
	public void removeIfStatEqual(IIntervalFormula intvF, double value) {
		ArrayList<Interval> newList = new ArrayList<Interval>();
		
		for (Interval intV : this.listInterval) {	
			if (intV.getStat(intvF) != value) {
				newList.add(intV);
			}
		}
		this.listInterval = newList;
	}
	

}
