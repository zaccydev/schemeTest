package formula.interval;

import scheme.ListScheme;
import scheme.Number;
import scheme.Interval;

public class FrequenceInterval extends AIntervalFormula {

	private int m_tNbr;  
    private boolean simpleName = true;
	
	public FrequenceInterval(int tNbr) {    	
    	super();
    	m_tNbr = tNbr;
    }

	public double getResults(Interval intv, ListScheme schemes) {	
		int freq = 0;         
        int max = m_tNbr + m_dt;       
        
        boolean bIntv = false; 
      
       	for (int i = m_dt; i < max; i++) {  
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
    		return "Freq Intv " + m_tNbr;
    	String 	dt = "";
    	if (m_dt >= 1)
    		dt = " de " + m_dt + " à ";
        return "Freq Intv " + dt + String.valueOf(m_tNbr + m_dt);   
    }

}
