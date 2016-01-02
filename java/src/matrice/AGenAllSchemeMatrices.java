package matrice;


public abstract class AGenAllSchemeMatrices implements IMatrixGenerator {
	
	protected int schemeSize = 0;	
	protected long matrixSize = 0;
	protected int currentMatrixSize = 1;
	protected byte[] firstScheme = null;	
	protected int nbNum = 0; 
	

	/**
	 * 
	 * @param nbNum : le nombre de numéro de l'univers
	 * @param schemeSize : le nombre de numéro pour chaque combinaisons générées
	 */
	public AGenAllSchemeMatrices (int nbNum, int schemeSize) {		
		
		this.schemeSize = schemeSize;
		this.matrixSize = getSize(nbNum, schemeSize);
		this.initFirstScheme();
		this.nbNum = nbNum;	
	}
		
	public void setFirstScheme(byte[] firstScheme) {
		this.firstScheme = firstScheme;
	}
	
	public long getSize(int nbNum, int schemeSize) {		
		double prob = 1.0;
		for (int i = 1; i <= schemeSize; i++) {
			prob *= ((double)i/ (nbNum - (schemeSize - i )));
		}
		
		return (long) (1/prob);
	}
	
	protected abstract void addScheme();

	private void initFirstScheme() {		
		firstScheme = new byte[schemeSize];
		
		for (byte i = 1; i <= schemeSize; i++) {
			firstScheme[i - 1] = i;
		}		
	}
	
	protected byte[] genScheme(byte[] scheme) {
		byte pass = 0;
		scheme = scheme.clone();
		
		while ( (scheme[schemeSize - 1 - pass] >= nbNum - pass) ) {	
			pass++;		
		}
		if (pass > 0) {
    		byte min = scheme[schemeSize - 1 - pass];
    		for (int i = schemeSize-pass, j=2; i < schemeSize; i++, j++) {
    			scheme[i] = (byte) (min + j);
    		}
		}
		scheme[schemeSize - 1 - pass]++;	
		
		return scheme;		
	}
}