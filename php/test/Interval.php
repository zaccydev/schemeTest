<?php

namespace test;

use Scheme\Interval;
use Scheme\Interval\AIntervalFormula;
use Scheme\ListScheme;

class IntervalTest extends \PHPUnit_Framework_TestCase {

  public function testConstructAndCountAndGetAndSet() {
    $interval = new Interval([4,18,25,42]);
    $this->assertTrue($interval->size() == 4);
    $this->assertTrue($interval->get(0) == 4);
    $this->assertTrue($interval->get(3) == 42);

    $interval->set(2,40);
    $this->assertTrue($interval->get(2) == 40);
  }

  public function testHasByte() {
    $interval = new Interval([4,18,25,42]);
    $this->assertTrue($interval->hasByte(25));
    $this->assertFalse($interval->hasByte(5));
  }

  public function testHasBytes() {
    $interval = new Interval([4,18,25,42]);
    $this->assertTrue($interval->hasBytes([18,25,42]));
    $this->assertTrue($interval->hasBytes([42,25,18]));
    $this->assertFalse($interval->hasBytes([4,18,26]));
  }

  public function testToString() {
    $interval = new Interval([4,18,25,42]);
    $this->assertTrue($interval->toString() == "4 - 18 - 25 - 42");
  }

  /*public function testSetAndGetStat() {
      $interval = new Interval([4,18,25,42]);
      
      }*/

}

/*class FakeIntervalFormula extends AIntervalFormula {

  public function getName() {
    return "fake-istat";
  }

  public function getResults(Interval $intv, ListScheme $schemes) {
    return $intv->get(0) * 2;
  }

  }*/