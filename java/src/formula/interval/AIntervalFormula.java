package formula.interval;

public abstract class AIntervalFormula implements IIntervalFormula {
	
	protected int m_dt = 0;	
	protected char m_sortingOrder = '>';

	public AIntervalFormula() {		
		m_dt = 0;	
	}

	public void setDistance(int dt) {
		m_dt = dt;
	}	

	public char getSortingOrder() {
		return m_sortingOrder;
	}

	private void setSortingOrder(char o) {
		m_sortingOrder = o;
	}

	public void setSortingOrderAsc() {
		this.setSortingOrder('>');
	}

	public void setSortingOrderDesc() {
		this.setSortingOrder('<');
	}
}
