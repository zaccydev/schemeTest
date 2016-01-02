package perslib;

public class Timer {
	
	private long m_start = 1L;	
	static int nbtimer = 1;
	
	public static Timer init() {
		
		return new Timer();
	}
	
	public void reinit() {
		m_start = System.currentTimeMillis();
	}
	
	public Timer() {
		m_start = System.currentTimeMillis();		
		nbtimer++;
	}
	
	public double startSince() {
		double temps = perslib._Math.round((((double) System.currentTimeMillis() - m_start) / 1000.0), 2);
		
		return temps;
	}	
}
