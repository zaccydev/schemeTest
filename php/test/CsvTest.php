<?php

namespace test;

use Scheme\CsvParse\ParseCsvResults;
use Scheme\ListScheme;
use Scheme\TypeJeu;

class CsvTest extends \PHPUnit_Framework_TestCase {
      
  public function testSetListScheme() {
    $listScheme = new ListScheme(TypeJeu::keno());
    $this->assertInstanceOf("Scheme\ListScheme", $listScheme);
  }
}