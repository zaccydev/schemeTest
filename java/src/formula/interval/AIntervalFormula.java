package formula.interval;

public abstract class AIntervalFormula implements IIntervalFormula {
	
	protected int dt = 0;	
	protected char sortingOrder = '>';

	public AIntervalFormula() {		
		dt = 0;	
	}

	public void setDistance(int dt) {
		this.dt = dt;
	}	

	public char getSortingOrder() {
		return sortingOrder;
	}

	private void setSortingOrder(char o) {
		sortingOrder = o;
	}

	public void setSortingOrderAsc() {
		this.setSortingOrder('>');
	}

	public void setSortingOrderDesc() {
		this.setSortingOrder('<');
	}
}
