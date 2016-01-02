<?php

namespace scheme\csvparse;

use Scheme\TypeJeu;
use Scheme\ListScheme;
use Scheme\Scheme;
use lib\Properties;

class ParseCsvResults {

  private $schemes;
  private $csvFilePath;
  private $game;
  private $csvLine;
  private $distance;

  public function __construct(TypeJeu $game, ListScheme $schemes) {		
    $baseFileName = Properties::get("scheme_data_dir");
    $this->game = $game;
    $this->schemes = $schemes;
    $this->distance = 0;		


    $this->csvFilePath = $baseFileName . "keno1.csv";
    $ak = [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
    $bk = [94];
    $this->csvFileParse($ak, $bk);
    $this->csvFilePath = $baseFileName . "keno2.csv";
    $this->csvFileParse($ak, $bk);
  }

  private function csvFileParse(array $idxNumbers, array $idxExtras) {

    if (!file_exists($this->csvFilePath)) {
      throw new \Exception("Fichier csv non trouvé à l'emplacement: " . $this->csvFilePath);
    }
    $dateCellIndex = 1;
    $lines = file($this->csvFilePath);	
    while (($line = $lines[$this->distance+1]) != null && $this->distance < 1101) {
      $this->csvLine = explode(";", $line);
      $scheme = new Scheme($this->game);
      $sDate = $this->csvLine[$dateCellIndex];
      $scheme->setDraw(\DateTime::createFromFormat('d/m/Y',$sDate),
			 $this->getMatchingLineNumbers($idxNumbers),
			 $this->getMatchingLineNumbers($idxExtras));
		
      $scheme->setDistance($this->distance);			
      $this->schemes->add($scheme);
      $this->distance++;
    }

  }

  private function getMatchingLineNumbers(array $idxCellsNumbers) {
    $cellsNumbers = [];

    foreach ($idxCellsNumbers as $key => $value) {
      $cellsNumbers[] = $this->csvLine[$value];
    }
    return $cellsNumbers;
  } 
}

