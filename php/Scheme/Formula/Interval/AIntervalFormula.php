<?php

namespace Scheme\Formula\Interval;

use Scheme\Formula\Interval\IIntervalFormula;

abstract class AIntervalFormula implements IIntervalFormula {
	
  protected $distance = 0;	
  protected $sortingOrder = '>';

  //public function __construct() {}

  public function setDistance($distance) {
    $this->distance = $distance;
  }	

  public function getSortingOrder() {
    return $this->sortingOrder;
  }

  private function setSortingOrder($o) {
    $this->sortingOrder = $o;
  }

  public function setSortingOrderAsc() {
    $this->setSortingOrder('>');
  }

  public function setSortingOrderDesc() {
    $this->setSortingOrder('<');
  }
}
