<?php

namespace test;

use Scheme\CsvParse\ParseCsvResults;
use Scheme\ListScheme;
use Scheme\TypeJeu;

class ListSchemeTest extends \PHPUnit_Framework_TestCase {
      
  public function testSize() {
    $listScheme = new ListScheme(TypeJeu::keno());
    $this->assertEquals(1101, $listScheme->size());
  }

  public function testGetScheme() {
    $listScheme = new ListScheme(TypeJeu::keno());
    $this->assertInstanceOf("\Scheme\Scheme", $listScheme->get(0));
    $this->assertInstanceOf("\Scheme\Scheme", $listScheme->get(85));
    $this->assertInstanceOf("\Scheme\Scheme", $listScheme->get(1001));
  }
}