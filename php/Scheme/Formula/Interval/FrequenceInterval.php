<?php

namespace Scheme\Formula\Interval;

use Scheme\ListScheme;
use Scheme\Number;
use Scheme\Interval;

class FrequenceInterval extends AIntervalFormula {

  private $tNbr;  
 
  public function __construct($tNbr) {            
    //parent::__construct();
    $this->tNbr = $tNbr;
  }

  public function getResults(Interval $intv, ListScheme $schemes) {   
    $freq = 0;         
    $max = $this->tNbr + $this->distance;               
    $bIntv = false; 
      
    for ($i = $this->distance; $i < $max; $i++) {  
      $j = 0;
      while ($j < $intv->size()) {
	$intvNumber = $intv->get($j);                
	if (! $schemes->get($i)->hasNumber(new Number($intvNumber, false))) {
	  $bIntv = false;
	  break;
	}
	$bIntv = true;
	$j++;
      }
      if ($bIntv) {
	$freq++;
      }
    }    
        
    return $freq;
  }

  public function getName() {
    return "Freq Intv " . $this->tNbr;
  }

}
