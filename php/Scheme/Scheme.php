<?php

namespace Scheme;

use Scheme\TypeJeu;
use Scheme\Number;

class Scheme {

  private $game;
  private $numbers; 
  private $distance;
  private $date;

  public function __construct(TypeJeu $game) {
    $this->game = $game;
    $this->numbers = [];
  }

  public function getGame() {
    return $this->game;
  }

  public function getDate() {
    return $this->date;
  }

  public function schemeDate() {
    return date('d/m/Y', $this->date->format('U'));
  }

  public function getDistance() {
    return $this->distance;
  }

  public function setDistance($distance) {
    return $this->distance = $distance;
  }

  private function addNumber(Number $n) {
    $this->numbers[] = $n;
  }

  private function setNumbers(array $numbers) {
    foreach ($numbers as $k => $i) {
      $this->addNumber(new Number($i, false));		       
    }
  }

  public function hasNumber(Number $needle) {
    foreach ($this->numbers as $k => $number) {
      if ($number->isEquals($needle)) {
	return true;
      }
    }
    return false;
  }

  public function getNumbers($extra) {
    $numbers = [];
    foreach ($this->numbers as $k => $number) {
      if ($extra == $number->isExtra()) {
	$numbers[] = $number->getValue();
      }
    }
    return $numbers;
  } 

  public function setDraw(\DateTime $date, array $numbers, array $extras) {
        
    $this->numbers = [];        
    $this->date = $date;       

    $this->setNumbers($numbers);

    foreach ($extras as $k => $i) {
      $this->numbers[] = new Number($i, true);
    }
  }

  public function toString() {
    $string = $this->buildStringFromScheme(false);
    
    if (count($this->getNumbers(true)) == 0) {
      return $string;
    } else {
      $string .= " ## ";
      $string .= $this->buildStringFromScheme(true);
    }

    return $string;
  }

  private function buildStringFromScheme($extra) {
    $string = "";
		
    foreach ($this->getNumbers($extra) as $k => $number) {
      $string .= $number;
      $string .= " - ";
    }
    $string = rtrim($string, ' - ');

    return $string;
  }
}
