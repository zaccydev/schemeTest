package formula.interval;

import scheme.Interval;
import formula.IFormula;
import scheme.ListScheme;

public interface IIntervalFormula extends IFormula {   
    
    public double getResults(Interval intv, ListScheme schemes) ;
        
    public void setDistance(int distance);    
    
    public char getSortingOrder();
    
    public void setSortingOrderAsc();
    
    public void setSortingOrderDesc();
}
