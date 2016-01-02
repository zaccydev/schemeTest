<?php

namespace scheme\matrice;

interface IMatrixGenerator {                
  public function setFirstScheme(array $firstScheme); 
  public function getSize($nbNum, $schemeSize); 
}
