<?php

namespace Scheme\Formula\Interval;

use Scheme\Interval;
use Scheme\Formula\IFormula;
use Scheme\ListScheme;

interface IIntervalFormula extends IFormula {   
    
    public function getResults(Interval $intv, ListScheme $schemes);
        
    public function setDistance($distance);    
    
    public function getSortingOrder();
    
    public function setSortingOrderAsc();
    
    public function setSortingOrderDesc();
}
