<?php

namespace test;

use Scheme\Matrice\GASM;

class MatriceTest extends \PHPUnit_Framework_TestCase {

  public function testSimpleMatrice() {
    $gasm = new GASM(10, 3);
    $this->assertEquals($gasm->getSize(10,3), $gasm->getSchemes()->size());
    $this->assertEquals("1 - 2 - 3", $gasm->getSchemes()->get(0)->toString());
    $this->assertEquals("1 - 2 - 4", $gasm->getSchemes()->get(1)->toString());

    /*for ($i = 0; $i < 100; $i++) {
      print($gasm->getSchemes()->get($i)->toString() . "\n");
      }*/
  }

}