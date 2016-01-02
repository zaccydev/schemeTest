package scheme;

import java.util.HashMap;

import perslib._Math;
import scheme.Interval;
import formula.IFormula;
import formula.interval.IIntervalFormula;


public class ListStats {
	
	private HashMap<String, Double> m_stats;
   
    public ListStats(SchemeBase scheme) {
        m_stats = new HashMap<String, Double>();     
    }   
    
    private double getStat(String statName) {
    	try {
        	return m_stats.get(statName);
        }
        catch (NullPointerException e) {
        	e.printStackTrace();
        }
        return 0;
    }
    
    public double getStat(IFormula stat) {
    	try {
        return getStat(stat.getName());
    	 }
        catch (NullPointerException e) {
        	//e.printStackTrace();
        	;
        }
        return 0;
    }
    
    public double getRoundStat(IFormula stat) {
    	return _Math.round(getStat(stat.getName()), 2);
    }
    public double getRoundStat(IFormula stat, int afterPoint) {
    	return _Math.round(getStat(stat.getName()), afterPoint);
    }
 
    
    public void setStat(IIntervalFormula stat, Interval intv, ListScheme ls) {
    	try {
    		m_stats.put(stat.getName(), stat.getResults(intv, ls));
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }  

}
