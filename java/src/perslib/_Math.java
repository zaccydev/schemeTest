package perslib;

public class _Math {

    public static double round(double dnum, int afterC) {
	
	if (afterC == 0) {
	    return ((dnum * 1) /1);
	}	
	double rounder = Math.pow(10, afterC);	
	return (Math.round(dnum * rounder) / rounder);
    }
}
