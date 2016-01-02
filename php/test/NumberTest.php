<?php

namespace test;

use Scheme\Number;

class NumberTest extends \PHPUnit_Framework_TestCase {
      
  public function testSetAndGetNumber() {
    $n1 = new Number(10, false);
    $this->assertEquals($n1->getValue(), 10);
    $this->assertEquals($n1->isExtra(), false);

    $n2 = new Number(5, true);
    $this->assertEquals($n2->getValue(), 5);
    $this->assertEquals($n2->isExtra(), true);

    $this->assertFalse($n2->IsEquals($n1));

    $n3 = new Number(10, false);
    $this->assertTrue($n1->IsEquals($n3));
  }
}