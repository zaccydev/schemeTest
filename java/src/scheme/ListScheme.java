package scheme;

import java.util.ArrayList;
import java.util.Iterator;

import csvparse.ParseCsvResultat;

public class ListScheme implements Iterable<Scheme> {

	private ArrayList<Scheme> m_schemes = new ArrayList<Scheme>();
	private ETypeJeu m_game;

	public ListScheme(ETypeJeu game) {
		m_game = game;
		try {
			new ParseCsvResultat(m_game, this);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Iterator<Scheme> iterator() {
		return m_schemes.iterator();
	}

	public void add(Scheme numero) {
		m_schemes.add(numero);
	}

	public Scheme get(int distance) throws IndexOutOfBoundsException {
		return m_schemes.get(distance);
	}

	public void remove(int index) {
		m_schemes.remove(index);
	}

	public int size() {
		return m_schemes.size();
	}

	public ETypeJeu getGame() {
		return m_game;
	}


}
