package scheme;



public class Number  {

	private int m_value;
    private boolean m_extra;
    private int m_emplacement;
    private ListStats m_listStats;
    
   /** Le score est utilisé avec Number principalement pour attribuer un numéro aléatoire.
    * ex.: Permet d'être un critère de classement de tri (si autres critères égaux). */
    private int m_score = 1;
 
    
    public Number (int value, boolean extra, int emplacement) {
        m_extra = extra;
        m_value = value;
        m_emplacement = emplacement;
        m_listStats = new ListStats(null);
    }    
    
    public boolean isExtra() {
        return m_extra;
    }
        
    public int getValue() {
        return m_value;
    }

    public int getScore() {
        return m_score;
    }   
    
    public int getFinale() {
    	
    	return (m_value % 10);
    }
    
    public void setScore(int score) {
        this.m_score = score;
    }    
    
    public int getEmplacement() {
        return m_emplacement;
    }  
    
    public void setPlacement(int placement) {
    	m_emplacement = placement;
    } 
    
    public ListStats getListStats() {
        return m_listStats;
    }
        
    public boolean isEqual(Number n) {
    	if (this.getValue() == n.getValue() && this.isExtra() == n.isExtra()) {
    		return true;
    	}
    	return false;
    }    
}
