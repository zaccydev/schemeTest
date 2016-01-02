<?php

namespace Scheme;

use Scheme\Interval;
use Scheme\Formula\IFormula;
use Scheme\Formula\Interval\IIntervalFormula;

class ListStats {
	
  private $stats;

  public function __construct() {
    $this->stats = [];
  }
   
    
  private function _getStat($statName) {

    if (isset($this->stats[$statName])) {
      return $this->stats[$statName];
    }
      
    return 0;
  }
    
  public function getStat(IFormula $stat) {

    return $this->_getStat($stat->getName());
  }
    
  public function getRoundStat(IFormula $stat, $afterPoint = -1) {
    $p = $afterPoint > -1 ? $afterPoint : 2;

    return round($this->_getStat($stat->getName()), $afterPoint);
  }
    
  public function setStat(IIntervalFormula $stat, Interval $intv, ListScheme $ls) {

    $this->stats[$stat->getName()] = $stat->getResults($intv, $ls);
  }
}  


