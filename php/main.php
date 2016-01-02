<?php

spl_autoload_register(function($className) {
    /** due to conflict with PSR-0 phpunit namespace,
     *  className with underscore are ignored     
     */
    if (preg_match("/_/", $className) === 1) 
      return;
    /** PSR-4 class with namespace classloader
     */
    $className=str_replace("\\","/",$className);
    require "./" . $className . '.php';
  });

if (!isset($argv) || count($argv) > 1) {
  return;
}

define("__NB_RESULT__", 100);

use Scheme\Matrice\GASM;
use Scheme\ListScheme;
use Scheme\TypeJeu;
use Scheme\Formula\Interval\FrequenceInterval;
use Scheme\Formula\Interval\ListInterval;
use Scheme\Formula\Interval\ListIntervalFormula;
use lib\Timer;

$gasm = new GASM(70, 3);
$lib = $gasm->getSchemes();
$lif = new ListIntervalFormula();
$ffi = new FrequenceInterval(__NB_RESULT__);
$ffi->setSortingOrderDesc();
$lif->add($ffi);
                
//parse les csv de résultat (2 fichiers au total)
$timer = new Timer();              
$schemes = new ListScheme(TypeJeu::keno());
                                
print("Nombre de tirages parsés depuis le csv : " . $schemes->size() . "\n"); 
print("Temps pour parser le csv de données : " . $timer->startSince() . "s\n");
$timer->reinit();
                
//calcul des statistiques et tri des résultats 
print("Nombre de combinaisons à analyser : " . ($lib->size() * __NB_RESULT__) . "\n");
$lif->setStats($lib, $schemes);
$lib->sortBy($lif);
print("Temps de l'analyse statistique et du tri des résultats : " . $timer->startSince() . "s\n");
for ($i = 0; $i < 3; $i++) {
  print($lib->get($i)->toString() . " -- freq: " . $lib->get($i)->getStat($ffi) . "\n");                    
}

