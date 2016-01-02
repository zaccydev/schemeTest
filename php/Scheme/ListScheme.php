<?php

namespace scheme;

use Scheme\CsvParse\ParseCsvResults;
use Scheme\TypeJeu;

class ListScheme {

  private $schemes;
  private $game;

  public function __construct(TypeJeu $game) {
    $this->game = $game;
    $this->schemes = [];  
    new ParseCsvResults($game, $this);   
  }

  public function add(Scheme $scheme) {
    $this->schemes[] = $scheme;
  }

  public function get($distance) {
    if (! (isset($this->schemes[$distance]))) {
      throw new Exception("Aucune combinaison de cette distance.");
    }
    return $this->schemes[$distance];
  }

  public function remove($index) {
    unset($this->schemes[$index]);
  }

  public function size() {
    return count($this->schemes);
  }

  public function getGame() {
    return $this->game;
  }
}
