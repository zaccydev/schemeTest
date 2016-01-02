<?php

namespace Scheme\Formula\Interval;

use Scheme\Interval;

class ListInterval {
	
  protected $listInterval;
		
  public function __construct(array $listInterval = []) {
    if (count($listInterval)) {
      $this->listInterval = $listInterval;
    } else {
      $this->listInterval = [];
    }
  }
	
  public function add(Interval $intv) {
    $this->listInterval[] = $intv;
  }
	
  public function size() {
    return count($this->listInterval);
  }
	
  public function remove ($idx) {
    unset($this->listInterval[$idx]);
  }

  public function get($index) {
    return $this->listInterval[$index];
  }
	
  public function sortBy(ListIntervalFormula $lif) {

    foreach ($this->listInterval as $key => $interval) {
      $interval->setComparatorsIIF($lif);			
    }
    usort($this->listInterval, ["Scheme\Interval", "compareTo"]);
  }
	
  public function getSortedInterval($nbIntv, ListIntervalFormula $lif) {		
    $il = new ListInterval();
		
    $this->sortBy($lif);

    for ($i = 0; $i < $nbIntv; $i++) {	
      if ($i < count($listInterval)) {
	$il->add($this->listInterval->get($i));
      }
    }
    return $il;
  }
	

}
