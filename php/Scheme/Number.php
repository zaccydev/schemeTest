<?php

namespace Scheme;

class Number {

    private $value;
    private $extra;   
  
    public function __construct ($value, $extra) {
        $this->extra = $extra;
        $this->value = $value;      
    }    
    
    public function isExtra() {
        return $this->extra;
    }
        
    public function getValue() {
        return $this->value;
    }
        
    public function isEquals(Number $n) {
    	if ($this->getValue() == $n->getValue() && $this->isExtra() == $n->isExtra()) {
    		return true;
    	}
    	return false;
    }    
}