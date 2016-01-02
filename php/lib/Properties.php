<?php

namespace lib;

class Properties {
  
  const PROP_FILE = "./../app.properties";

  static function get($name) {
    foreach (file(self::PROP_FILE) as $key => $value) {
      if (trim(strtok($value, "=")) == $name ) {
	return trim(strtok("="));
      }
    }
  }
}