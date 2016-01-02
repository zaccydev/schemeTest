package scheme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SchemeBase {

	protected ETypeJeu m_game;
	protected ArrayList<Number> m_numeros;
	protected ListStats m_listStat;
	protected int m_dt = 0;
	protected Date m_date;    

	public SchemeBase(ETypeJeu game) {
		m_game = game;
		m_listStat = new ListStats(this);
		m_numeros = new ArrayList<Number>();
	}
	
	public ETypeJeu getGame() {
		return m_game;
	}

	public String dateScheme() {
        return new SimpleDateFormat("dd/MM/yyyy").format(m_date);
    }
	 
    public Date getDate() {
    	return m_date;
    }

	public int getDt() {
		return m_dt;
	}
	
	public void setDt(int dist) {
		m_dt = dist;
	}

	public void addNumber(Number n) {		
		m_numeros.add(n);		
	}

	public void setNumbers(Integer[] numeros) {

		for (int i : numeros) {
			m_numeros.add(new Number(i, false, 0));
		}
	}

	public ListStats getListStats() {
		return m_listStat;
	}

	public boolean hasNumber(Number needle, int placement) {

		for (Number number : m_numeros) {
			if (number.isExtra() == needle.isExtra() && number.getValue() == needle.getValue()
					&& (placement == number.getEmplacement() || placement == 0)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String string = new String();
		
		for (int num : getNumbers(false))
			string += num + " - ";
		string = string.substring(0, string.length() - 2);
		
		if (getNumbers(true).size() > 0) {
			string += " ## ";
			for (int num : getNumbers(true))
				string += num + " - ";
			string = string.substring(0, string.length() - 2);
		}
		return string;
	}
	
	public String toStringNoExtra() {
		String string = new String();
		
		for (int num : getNumbers(false))
			string += num + " - ";
		string = string.substring(0, string.length() - 2);
		
		return string;
	}
	
	
	public int hasHowManyNumbersAmongXScheme(SchemeBase xsb) {
		int find = 0;
		for (Number n : m_numeros) {
			if (! n.isExtra() && xsb.hasNumber(n, 0)) {
				find++;
			}
		}	
		return find;
	}
	
	public Number getNumberByValue(int value, boolean extra) throws Exception{
		for (Number n : m_numeros) {
			if (n.isExtra() == extra && n.getValue() == value) {
				return n;
			}
		}
		throw new Exception("Aucun numéro avec cette valeur ("+value+")."); 
	}
	

	/*
	 * TODO: Revoir le nom des fonctions getSchemeNumbers() et getNumbers
	 */
	public ArrayList<Number> getSchemeNumbers() {
		return m_numeros;
	}
	
	public ArrayList<Integer> getNumbers(boolean extra) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (Number num : m_numeros) {
			if (extra == num.isExtra()) {
				numbers.add(num.getValue());
			}
		}
		return numbers;
	}

	
	/**
	 * Retourne le nombre de numéros (sans les extras);
	 * @return
	 */
	public int getNumberCount() {
		return getNumbers(false).size();
	}

	public int getSortedNumberAsc(int pos, boolean extra) {
		ArrayList<Integer> nums = getNumbers(extra);
		
		Collections.sort(nums);
		
		return nums.get(pos);		
	}
	public int getSortedNumberDesc(int pos, boolean extra) {
		ArrayList<Integer> nums = getNumbers(extra);
		
		Collections.sort(nums);
		
		return nums.get(nums.size() - 1 + pos);
	}

	public Double getMediane(boolean extra) {
		double sum = 0;

		for (int n : getNumbers(extra)) {
			sum += n;
		}
		return (sum / (double) getNumbers(extra).size());
	}

	public int[] getTenthDistrib() {
		int tDt[] = new int[(m_game.getNbTotalNum() / 10) + 1];
		double idx = 0;

		for (int i : this.getNumbers(false)) {

			idx = (double) i / 10.0;

			if (idx < 1) {
				tDt[0]++;
			//Keno, Em : Le 70, 50 sont comptés avec les nombres < à 10
			} else if (i == m_game.getNbTotalNum() && ! m_game.equals(ETypeJeu.Loto)) {
				tDt[0]++;
			} else {
				tDt[(int) idx] += 1;
			}
		}
		return tDt;
	}

}
