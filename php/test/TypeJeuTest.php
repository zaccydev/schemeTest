<?php

use Scheme\TypeJeu;
use Scheme\Number;

class TypeJeuTest extends \PHPUnit_Framework_TestCase {
      
  function testKenoInitAndGetVal() {
    $a = TypeJeu::keno();
    $this->assertEquals($a->getNbTotalNum(), 70);
    $this->assertEquals($a->getNbTotalExtra(), 6);
    $this->assertEquals($a->getNbExtra(), 1);
    $this->assertEquals($a->getNbResultNum(), 20);
  }

  function testGetKenoListNumber() {
    $a = TypeJeu::keno();
    $testNumber = new Number(10, false);
    $this->assertTrue($testNumber->isEquals($a->getListNumber(false)[9]));
    $testNumber = new Number(70, false);
    $this->assertTrue($testNumber->isEquals($a->getListNumber(false)[69]));

    $testNumber = new Number(10, false);
    $this->assertFalse($testNumber->isEquals($a->getListNumber(true)[5]));

  }

  function testGetKenoListNumberExtra() {
    $a = TypeJeu::keno();
    $testNumber = new Number(10, true);
    $this->assertTrue($testNumber->isEquals($a->getListNumber(true)[5]));
  }
}