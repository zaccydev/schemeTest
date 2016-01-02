package csvparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import perslib._Properties;
import scheme.ETypeJeu;
import scheme.ListScheme;
import scheme.Scheme;

public class ParseCsvResultat {

	private ListScheme m_tirages;
	private String m_csvFilePath;
	private ETypeJeu m_game;
	private String[] m_csvLine;
	private int m_distanceTirage;
	private boolean isEuroMillion = false;

	public ParseCsvResultat(ETypeJeu game, ListScheme tirages) throws Exception {		
		String baseFileName = _Properties.loadProperties().getProperty("scheme_data_dir", "null");		
		m_game = game;
		m_tirages = tirages;
		m_distanceTirage = 0;		

		switch (game) {
		case Loto:
			m_csvFilePath = baseFileName + "nouveau_loto.csv";
			csvFileParse(new int[] { 4, 5, 6, 7, 8 }, new int[] { 9 });
			break;
		case EuroMillion:
			isEuroMillion = true;
			int a[] = {4, 5, 6, 7, 8}, b[] = {9, 10};			
			m_csvFilePath = baseFileName + "euromillions_3.csv";
			csvFileParse(a, b);
			m_csvFilePath = baseFileName + "euromillions_2.csv";			
			csvFileParse(a, b);
			break;
		case Keno:
			m_csvFilePath = baseFileName + "keno1.csv";
			int[] ak = new int[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
			int[] bk = new int[] { 94 };
			csvFileParse(ak, bk);
			m_csvFilePath = baseFileName + "keno2.csv";
			csvFileParse(ak, bk);
			break;
		default:
			throw new Exception("Type de jeux inattendu");
		}
	}

	private void csvFileParse(int[] idxCellsNumeros, int[] idxCellsExtras) throws Exception {

		File csvFile = new File(m_csvFilePath);
		if (!csvFile.exists()) {
			throw new Exception("Fichier csv non trouvé à l'emplacement: " + csvFile);
		}

		int dateCellIndex = 2;
		if (m_game == ETypeJeu.Keno)
			dateCellIndex = 1;
		
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));

		String line = null;
		reader.readLine();
		while ((line = reader.readLine()) != null && m_distanceTirage < 1101) {
			m_csvLine = line.split(";");

			Scheme tirage = new Scheme(m_game);
			String sDate = m_csvLine[dateCellIndex] + " 20:30:00";
			tirage.setTirage(new SimpleDateFormat("dd/MM/yyyy H:m:s").parse(sDate), getMatchingLineNumbers(idxCellsNumeros),
					getMatchingLineNumbers(idxCellsExtras));
			if (this.isEuroMillion) {
				tirage.setMyMillion(m_csvLine[m_csvLine.length - 2]);
			}
			tirage.setDt(m_distanceTirage);			
			m_tirages.add(tirage);

			m_distanceTirage++;
		}
		reader.close();
	}

	private int[] getMatchingLineNumbers(int[] idxCellsNumbers) {
		int[] cellsNumbers = new int[idxCellsNumbers.length];
		int i = 0;
		for (int idx : idxCellsNumbers) {
			cellsNumbers[i] = Integer.parseInt(m_csvLine[idx]);
			i++;
		}

		return cellsNumbers;
	}
}
