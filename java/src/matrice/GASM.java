package matrice;

import scheme.Interval;
import formula.interval.ListInterval;

public class GASM extends AGenAllSchemeMatrices {
			
		private ListInterval schemes;
	
		protected void addScheme() {
			byte[] scheme = firstScheme;
			
			while (scheme[0] <= nbNum - schemeSize) {	
				scheme = genScheme(scheme);
				schemes.add(new Interval(scheme));				
			}
		}
			
		public long getSize(int nbNum, int schemeSize) {		
			double prob = 1.0;
			
			for (int i = 1; i <= schemeSize; i++) {
				prob *= ((double)i/ (nbNum - (schemeSize - i )));
			}
			
			return (long) (1/prob);
		}
		
		public ListInterval getSchemes() {
			
			addScheme();			
			return this.schemes;
		}	
				
		public GASM(int nbNum, int schemeSize) {
			super(nbNum, schemeSize);
			schemes = new ListInterval();
			schemes.add(new Interval(firstScheme));
		}
	}

