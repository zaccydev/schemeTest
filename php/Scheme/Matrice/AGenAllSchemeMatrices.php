<?php

namespace Scheme\Matrice;

use Scheme\Matrice\IMatrixGenerator;

abstract class AGenAllSchemeMatrices implements IMatrixGenerator {
        
  protected $schemeSize = 0;   
  protected $matrixSize = 0;
  protected $currentMatrixSize = 1;
  protected $firstScheme = null;    
  protected $nbNum = 0; 
        
  /**
   * 
   * @param nbNum : le nombre de numéro de l'univers
   * @param schemeSize : le nombre de numéro pour chaque combinaisons générées
   */
  public function __construct ($nbNum, $schemeSize) {              
                
    $this->schemeSize = $schemeSize;
    $this->matrixSize = $this->getSize($nbNum, $schemeSize);
    $this->initFirstScheme();
    $this->nbNum = $nbNum;     
  }
                
  public function setFirstScheme(array $firstScheme) {
    $this->firstScheme = $firstScheme;
  }
        
  public function getSize($nbNum, $schemeSize) {                
    $prob = 1.0;
    for ($i = 1; $i <= $schemeSize; $i++) {
      $prob *= ($i / ($nbNum - ($schemeSize - $i )));
    }
                
    return (1/$prob);
  }

  protected abstract function addScheme();

  private function initFirstScheme() {                
    $this->firstScheme = [];
                
    for ($i = 1; $i <= $this->schemeSize; $i++) {
      $this->firstScheme[$i - 1] = $i;
    }               
  }
        
  protected function genScheme(array $scheme) {
    $pass = 0;
                
    while ( ($scheme[$this->schemeSize - 1 - $pass] >= $this->nbNum - $pass) ) {     
      $pass++;         
    }
    if ($pass > 0) {
      $min = $scheme[$this->schemeSize - 1 - $pass];
      for ($i = $this->schemeSize - $pass, $j=2; $i < $this->schemeSize; $i++, $j++) {
	$scheme[$i] = $min + $j;
      }
    }
    $scheme[$this->schemeSize - 1 - $pass]++;        
                
    return $scheme;          
  }
}
