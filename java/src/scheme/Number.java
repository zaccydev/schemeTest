package scheme;



public class Number  {

	private int value;
    private boolean extra;
    private int emplacement;
    private ListStats listStats;
    
   /** Le score est utilisé avec Number principalement pour attribuer un numéro aléatoire.
    * ex.: Permet d'être un critère de classement de tri (si autres critères égaux). */
    private int m_score = 1;
 
    
    public Number (int value, boolean extra, int emplacement) {
        this.extra = extra;
        this.value = value;
        this.emplacement = emplacement;
        this.listStats = new ListStats(null);
    }    
    
    public boolean isExtra() {
        return extra;
    }
        
    public int getValue() {
        return value;
    }

    public int getScore() {
        return m_score;
    }   
    
    public int getFinale() {
    	
    	return (value % 10);
    }
    
    public void setScore(int score) {
        this.m_score = score;
    }    
    
    public int getEmplacement() {
        return emplacement;
    }  
    
    public void setPlacement(int placement) {
    	this.emplacement = placement;
    } 
    
    public ListStats getListStats() {
        return listStats;
    }
        
    public boolean isEqual(Number n) {
    	if (this.getValue() == n.getValue() && this.isExtra() == n.isExtra()) {
    		return true;
    	}
    	return false;
    }    
}
