package formula.interval;

import scheme.ListScheme;
import scheme.Number;
import scheme.Interval;

public class FrequenceInterval extends AIntervalFormula {

	private int tNbr;  
    private boolean simpleName = true;
	
	public FrequenceInterval(int tNbr) {    	
    	super();
    	this.tNbr = tNbr;
    }

	public double getResults(Interval intv, ListScheme schemes) {	
		int freq = 0;         
        int max = this.tNbr + dt;       
        
        boolean bIntv = false; 
      
       	for (int i = dt; i < max; i++) {  
            for (byte intvNumber : intv.getInterval()) {            	
            	if (! schemes.get(i).hasNumber(new Number(intvNumber, false, 0), 0)) {
            		bIntv = false;
            		break;
            	}
            	bIntv = true;
            }
           if (bIntv) {
        	   freq++;
           }
        }    
        
        return freq;
	}

	public String getName() {
        
    	if (simpleName)
    		return "Freq Intv " + tNbr;
    	String 	dt = "";
    	if (this.dt >= 1)
    		dt = " de " + this.dt + " à ";
        return "Freq Intv " + dt + String.valueOf(tNbr + dt);   
    }

}
