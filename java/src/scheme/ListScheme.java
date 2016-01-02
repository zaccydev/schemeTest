package scheme;

import java.util.ArrayList;
import java.util.Iterator;

import csvparse.ParseCsvResults;

public class ListScheme implements Iterable<Scheme> {

	private ArrayList<Scheme> schemes = new ArrayList<Scheme>();
	private ETypeJeu game;

	public ListScheme(ETypeJeu game) {
		this.game = game;
		try {
			new ParseCsvResults(game, this);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Iterator<Scheme> iterator() {
		return schemes.iterator();
	}

	public void add(Scheme numero) {
		schemes.add(numero);
	}

	public Scheme get(int distance) throws IndexOutOfBoundsException {
		return schemes.get(distance);
	}

	public void remove(int index) {
		schemes.remove(index);
	}

	public int size() {
		return schemes.size();
	}

	public ETypeJeu getGame() {
		return game;
	}


}
