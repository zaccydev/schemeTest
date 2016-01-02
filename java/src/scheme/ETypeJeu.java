package scheme;

import java.util.ArrayList;


public enum ETypeJeu {
	Loto(49, 10, 1, 5, 5), EuroMillion(50, 11, 2, 5, 5), Keno(70, 6, 1, 20, 7);

	private int nbTotalNum;
	private int nbTotalExtra;
	private int nbExtra;
	private int nbResultNum;
	private int nbTenth;
	
	private ETypeJeu(int nbTotalNum, int nbTotalExtra, int nbExtra, int nbResultNum, int nbTenth) {
		this.nbTotalNum = nbTotalNum;
		this.nbTotalExtra = nbTotalExtra;
		this.nbExtra = nbExtra;
		this.nbResultNum = nbResultNum;
		this.nbTenth = nbTenth;
	}

	public int getNbTotalNum() {
		return nbTotalNum;
	}

	public int getNbTotalExtra() {
		return nbTotalExtra;
	}

	public int getNbExtra() {
		return nbExtra;
	}

	public int getNbResultNum() {
		return nbResultNum;
	}

	public int getNbTenth() {
		return nbTenth;
	}

	public ArrayList<Number> getListNumber(boolean extra, int placement) {
		ArrayList<Number> listNumber = new ArrayList<Number>();

		if (placement > 0) {
			if (! extra) 
    			for (int y = 1; y <= this.getNbResultNum(); y++) {
    				for (int i = 1; i <= this.getNbTotalNum(); i++) {
    					listNumber.add(new Number(i, false, y));
    				}
    			}
			else
    			for (int y = 1; y <= this.getNbExtra(); y++) {
    				for (int i = 1; i <= this.getNbTotalExtra(); i++) {
    					listNumber.add(new Number(i, true, y));
    				}
    			}
		}
		else {
			if (! extra) 
    			for (int i = 1; i <= this.getNbTotalNum(); i++) {
    				listNumber.add(new Number(i, false, 0));
    			}
			else {
				if (this.equals(ETypeJeu.Keno)) {
					for (int i : new int[]{1,2,3,4,5,10}) {
						listNumber.add(new Number(i, true, 0));
					}
				}
				else {
					for (int i = 1; i <= this.getNbTotalExtra(); i++) {
						listNumber.add(new Number(i, true, 0));
					}
				}
			}
		}

		return listNumber;
	}
	
}
