<?php

namespace scheme\matrice;

use Scheme\Interval;
use Scheme\Matrice\AGenAllSchemeMatrices;
use Scheme\Formula\Interval\ListInterval;

class GASM extends AGenAllSchemeMatrices {
                        
  private $schemes;

  public function __construct($nbNum, $schemeSize) {
    parent::__construct($nbNum, $schemeSize);
    $this->schemes = new ListInterval();
    $this->schemes->add(new Interval($this->firstScheme));
  }
                
  public function getSchemes() {                        
    $this->addScheme();                    
    return $this->schemes;
  }             
        
  protected function addScheme() {
    $scheme = $this->firstScheme;
                        
    while ($scheme[0] <= $this->nbNum - $this->schemeSize) {       
      $scheme = $this->genScheme($scheme);
      $this->schemes->add(new Interval($scheme));                              
    }
  }                     
}
