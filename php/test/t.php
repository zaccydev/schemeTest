<?php

$s = "abc - ";
$s = rtrim($s, " - ");
echo $s . "*\n";

$date = \DateTime::createFromFormat('d/m/Y','24/01/2015');
echo $date->format('U') . "*\n";
echo date('d/m/Y', $date->format('U')) . "*\n";


