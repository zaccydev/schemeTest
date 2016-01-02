<?php

namespace Scheme\Formula\Interval;

use Scheme\ListScheme;
use Scheme\Interval;

class ListIntervalFormula {

  private $listFormula;
  private $distance = 0;
	
  public function __construct() {
    $this->listFormula = [];
  }
	
  public function setDistance($distance) {
    $this->distance = $distance;
		
    foreach ($this->listFormula as $key => $formula) {
      $formula->setDistance($this->distance);
    }
  }
	
  public function getDistance() {
    return $this->distance;
  }
  /**
   * Ajouter une formule (en fin de liste).
   * @param fml
   */
  public function add(IIntervalFormula $fml) {
    $fml->setDistance($this->distance);
    $this->listFormula[] = $fml;		
  }
	
  /**
   * Ajoute la formule en début de liste.
   * @param fml
   */
  public function unshift(IIntervalFormula $fml) {
    $fml->setDistance($this->distance);
    unshift($this->listFormula, $fml);		
  }

  public function size() {
    return count($this->listFormula);
  }

  public function get($idx) {
    return $this->listFormula[$idx];
  }
	
  public function setStats(ListInterval $listInterval, ListScheme $schemes) {
			
    /*foreach ($listInterval as $key => $interval) {
      foreach ($this->listFormula as $kkey => $formula) {
	$interval->setStat($formula, $schemes);
      }
      }*/
    $i = 0;
    while ($i < $listInterval->size()) {
      $j = 0;
      while ($j < count($this->listFormula)) {
	$listInterval->get($i)->setStat($this->listFormula[$j], $schemes);
	$j++;
      }
      $i++;
    }

  }

}
