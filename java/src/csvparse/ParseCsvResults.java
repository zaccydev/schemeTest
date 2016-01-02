package csvparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import perslib._Properties;
import scheme.ETypeJeu;
import scheme.ListScheme;
import scheme.Scheme;

public class ParseCsvResults {

	private ListScheme schemes;
	private String csvFilePath;
	private ETypeJeu game;
	private String[] csvLine;
	private int distance;

	public ParseCsvResults(ETypeJeu game, ListScheme schemes) throws Exception {		
		String baseFileName = _Properties.loadProperties().getProperty("scheme_data_dir", "null");		
		this.game = game;
		this.schemes = schemes;
		this.distance = 0;		

		switch (game) {
		
		case Keno:
			csvFilePath = baseFileName + "keno1.csv";
			int[] ak = new int[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
			int[] bk = new int[] { 94 };
			csvFileParse(ak, bk);
			csvFilePath = baseFileName + "keno2.csv";
			csvFileParse(ak, bk);
			break;
		default:
			throw new Exception("Type de jeux inattendu");
		}
	}

	private void csvFileParse(int[] idxCellsNumeros, int[] idxCellsExtras) throws Exception {

		File csvFile = new File(csvFilePath);
		if (!csvFile.exists()) {
			throw new Exception("Fichier csv non trouvé à l'emplacement: " + csvFile);
		}

		int dateCellIndex = 2;
		if (game == ETypeJeu.Keno)
			dateCellIndex = 1;
		
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));

		String line = null;
		reader.readLine();
		while ((line = reader.readLine()) != null && distance < 1101) {
			csvLine = line.split(";");

			Scheme scheme = new Scheme(game);
			String sDate = csvLine[dateCellIndex];
			scheme.setTirage(new SimpleDateFormat("dd/MM/yyyy").parse(sDate), getMatchingLineNumbers(idxCellsNumeros),
					getMatchingLineNumbers(idxCellsExtras));

			scheme.setDt(distance);			
			schemes.add(scheme);

			distance++;
		}
		reader.close();
	}

	private int[] getMatchingLineNumbers(int[] idxCellsNumbers) {
		int[] cellsNumbers = new int[idxCellsNumbers.length];
		int i = 0;
		for (int idx : idxCellsNumbers) {
			cellsNumbers[i] = Integer.parseInt(csvLine[idx]);
			i++;
		}

		return cellsNumbers;
	}
}
