<?php

namespace test;

use Scheme\TypeJeu;
use Scheme\Number;
use Scheme\Scheme;

class SchemeTest extends \PHPUnit_Framework_TestCase {
  
  function testGameScheme() {
    $scheme = new Scheme(TypeJeu::keno());  
    $this->assertEquals($scheme->getGame()->getNbTotalNum(), 70); 
  }

  function testSchemeCreate() {
    $scheme = new Scheme(TypeJeu::keno());  
    $scheme->setDraw(new \DateTime(), [1,2,3,4,5,6,7,8,9,10], [3] );
    $this->assertEquals(count($scheme->getNumbers(false)) + count($scheme->getNumbers(true)), 11);

    $scheme->setDistance(10);
    $this->assertEquals($scheme->getDistance(), 10);

    $this->assertTrue($scheme->hasNumber(new Number(5, false)));
    $this->assertTrue($scheme->hasNumber(new Number(3, true)));

    $this->assertFalse($scheme->hasNumber(new Number(10, true)));

    $this->assertCount(10, $scheme->getNumbers(false));
  }

  function testSchemeString() {
    $scheme = new Scheme(TypeJeu::keno());  
    $scheme->setDraw(new \DateTime(), [1,18,43], [5] );
    $stringShouldBe = "1 - 18 - 43 ## 5";
    $this->assertEquals($scheme->toString(), $stringShouldBe);

    $scheme = new Scheme(TypeJeu::keno());  
    $scheme->setDraw(new \DateTime(), [1,18,43], [] );
    $stringShouldBe = "1 - 18 - 43";
    $this->assertEquals($scheme->toString(), $stringShouldBe);
  }

  function testDateScheme() {
    $scheme = new Scheme(TypeJeu::keno());  
    $date = \DateTime::createFromFormat('d/m/Y','24/01/2015');
    $scheme->setDraw($date, [1,18,43], [5]);
    $this->assertEquals(date('d/m/Y', $date->format('U')), $scheme->schemeDate());
    $this->assertEquals($date, $scheme->getDate());

  }

}