<?php

namespace Scheme;

use Scheme\Number;

class TypeJeu {

  private static $nbTotalNum;
  private static $nbTotalExtra;
  private static $nbExtra;
  private static $nbResultNum;
  private static $isKeno = false;

  public static function keno() {
    $game = [70, 6, 1, 20];
    self::$isKeno = true;
    return new TypeJeu($game);
  }
  private function __construct($game) {
    self::$nbTotalNum = $game[0];
    self::$nbTotalExtra = $game[1];
    self::$nbExtra = $game[2];
    self::$nbResultNum = $game[3];
  }

  public function getNbTotalNum() {
    return self::$nbTotalNum;
  }

  public function getNbTotalExtra() {
    return self::$nbTotalExtra;
  }

  public function getNbExtra() {
    return self::$nbExtra;
  }

  public function getNbResultNum() {
    return self::$nbResultNum;
  }

  public function getListNumber($extra) {
    $listNumber = [];    
    
    if (! $extra) {
      for ($i = 1; $i <= $this->getNbTotalNum(); $i++) {
	$listNumber[] = new Number($i, false);
      }
    } else {
      if (self::$isKeno) {
	foreach ([1,2,3,4,5,10] as $k => $i) {
	  $listNumber[] = new Number($i, true);
	}
      } else {
	for ($i = 1; $i <= $this->getNbTotalExtra(); $i++) {
	  $listNumber[] = new Number($i, true);
	}
      }
     
    }

    return $listNumber;   
  }

}

/*$a = TypeJeu::keno();
$testNumber = new Number(10, true);
var_dump($a->getListNumber(true));*/