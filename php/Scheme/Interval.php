<?php

namespace Scheme;

use Scheme\Listats;
use Scheme\Formula\Interval\ListIntervalFormula;
use Scheme\Formula\Interval\IIntervalFormula;

class Interval {

  private $interval;
  private $listStats;
  static $comparatorsIIF;

  public function __construct(array $interval) {
    $this->interval = $interval;
    $this->listStats = new ListStats;
    $this->comparatorsIIF = null;
  }

  public function hasByte($b) {

    return in_array($b, $this->interval);
  }

  public function hasBytes(array $bytes) {
    foreach ($bytes as $k => $byte) {
      if (! $this->hasByte($byte)) {
	return false;
      }
    }

    return true;
  }

  public function size() {

    return count($this->interval);
  }

  public function get($idx) {

    return $this->interval[$idx];
  }

  public function set($idx, $value) {
    $this->interval[$idx] = $value;
  }

  public function toString() {
    $string = "";

    for ($i = 0; $i < count($this->interval); $i++)
      $string .= $this->interval[$i] . " - ";
		
    return rtrim($string, " - ");
  }
	
  public function getListStats() {
    return $this->listStats;
  }
    
  public function setStat(IIntervalFormula $fml, ListScheme $ls) {
    $this->listStats->setStat($fml, $this, $ls);
  }
    
  public function getStat(IIntervalFormula $fml) {     	
    return $this->listStats->getStat($fml);    	
  }

  static function setComparatorsIIF(ListIntervalFormula $lnf) {
    self::$comparatorsIIF = $lnf;
  }   
    
  static function compareTo(Interval $intvA, Interval $intvB) {
        
    if (self::$comparatorsIIF == null) {
      throw new \Exception("Comparators not set");
    }
    return self::_compareTo($intvA, $intvB, 0);
  }    
    
  static function compareInfWithOrder(Interval $intvA, Interval $intvB, $nestLevel) {
    $comp = 0;
    $inf = self::$comparatorsIIF->get($nestLevel);
    $order = $inf->getSortingOrder();
    	
    if ($order == '<' || $order != '>') {
      if ($intvB->getStat($inf) < $intvA->getStat($inf)) {
	$comp = -1;
      }
      else if ($intvB->getStat($inf) > $intvA->getStat($inf)) {
	$comp = 1;
      }
      else {
	$comp = 0;    	
      }
    } 
    else {
      if ($intvB->getStat($inf) > $intvA->getStat($inf)) {
	$comp = -1;
      }
      else if ($intvB->getStat($inf) < $intvA->getStat($inf)) {
	$comp = 1;
      }
      else {
	$comp = 0;   
      }
    }
    	
    return $comp;
  }
 	
  static function _compareTo(Interval $intvA, Interval $intvB, $nestLevel) {    	
    $comp = 0;
    	
    while ($nestLevel < self::$comparatorsIIF->size() && $comp == 0) {
      $comp = self::compareInfWithOrder($intvA, $intvB, $nestLevel);
      $nestLevel++;
    }    	
    	
    return $comp;
  }


}