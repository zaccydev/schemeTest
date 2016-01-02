package scheme;

import formula.interval.IIntervalFormula;
import formula.interval.ListIntervalFormula;

public class Interval implements Comparable<Interval> {
	
	protected byte[] interval;
	
	private int score = 0;
	private ListStats listStats;  
	
	public boolean hasByte(byte b) {
		
		for (int i = 0; i < interval.length; i++) {
			if (b == interval[i])
				return true;
		}
		
		return false;
	}
	
	public boolean hasBytes(byte[] bytes) {
		
		for (byte b : bytes) {
			if (! this.hasByte(b)) {				
				return false;
			}
		}		
		return true;
	}
	
	public boolean has1BytesFrom(byte[] bytes) {
		
		for (byte b : bytes) {
			if (this.hasByte(b)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasSameNumber(Interval needle) {
				
		if (this.length() != needle.length())
			return false;
		int find = 0;
		for (byte thisbyte : interval) {
			boolean findSearched = false;
			for (byte searchedByte : needle.getInterval()) {
				if (thisbyte == searchedByte)
					findSearched = true;				
			}
			if (! findSearched)
				break;
			
			find++;
			if (find == needle.length())
				return true;			
		}
		
		return false;
	}
		
	public boolean hasSameNumberLinearCheck(Interval needle) {
		
		if (interval.length != needle.length())
			return false;
		for (int i = 0; i < interval.length; i++) {
			if (interval[i] != needle.get(i))
				return false;
		}
		return true;
	}	
	
	public Interval(byte[] interval) {
		this.interval = interval;
		
		this.listStats = new ListStats(null);
	}	
		
	public byte[] getInterval() {
		return interval;
	}		
	
	public int length() {
		return interval.length;
	}
	
	public byte get(int idx) {
		return interval[idx];
	}
	
	public void set(int idx, int value) {
		interval[idx] = (byte) value;
	}

	public void scorePlus1() {
		score++;
	}	
	
	public int getScore() {
		return this.score;
	}

	public String toString() {
		String string = "";

		for (int i = 0; i < interval.length; i++)
			string += interval[i] + " - ";
		
		return string.substring(0, string.length() - 2);		 
	}	
	
	
    private ListIntervalFormula comparatorsIIF = null;
	
    public ListStats getListStats() {
        return listStats;
    }
    
    public void setStat(IIntervalFormula fml, ListScheme ls) {
    	listStats.setStat(fml, this, ls);
    }
    
    public double getStat(IIntervalFormula fml) {     	
    	return listStats.getStat(fml);    	
    }
 	
    public void setComparatorsIIF(ListIntervalFormula lnf) {
    	comparatorsIIF = lnf;
    }   
    
    public int compareTo(Interval intv) {
        
    	if (comparatorsIIF == null) {
    		if (intv.getScore() > this.getScore()) {
    			return -1;
    		}
    		return 1;
    	}
    	return this.compareTo(intv, 0);
	}    
    
    private int compareInfWithOrder(Interval intv, int nestLevel) {
    	int comp = 0;
    	IIntervalFormula inf = comparatorsIIF.get(nestLevel);
    	char order = inf.getSortingOrder();
    	
    	if (order == '<' || order != '>') {
    		if (intv.getStat(inf) < this.getStat(inf)) {
    			comp = -1;
    		}
    		else if (intv.getStat(inf) > this.getStat(inf)) {
    			comp = 1;
    		}
    		else {
    			comp = 0;    	
    		}
    	} 
    	else {
    		if (intv.getStat(inf) > this.getStat(inf)) {
    			comp = -1;
    		}
    		else if (intv.getStat(inf) < this.getStat(inf)) {
    			comp = 1;
    		}
    		else {
    			comp = 0;   
    		}
    	}
    	
    	return comp;
    }
    
    
    private int compareTo(Interval intv, int nestLevel) {    	
    	int comp = 0;
    	
    	while (nestLevel < comparatorsIIF.size() && comp == 0) {
    		comp = this.compareInfWithOrder(intv, nestLevel);
    		nestLevel++;
    	}    	
    	
    	return comp;
    }
}
