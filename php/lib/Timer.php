<?php

namespace lib;

class Timer {
	
  private $start = 0;	
	
  public function __construct() {
    $this->start = \microtime(true);		
  }
	
  public function reinit() {
    $this->start = \microtime(true);
  }
	
  public function startSince() {
    $time = round(\microtime(true) - $this->start, 2);
		
    return $time;
  }	
}