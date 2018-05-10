<?php
header('Content-Type: text/html; charset=utf-8');
session_start();
ini_set('display_errors',1);  
error_reporting(E_ERROR | E_PARSE);

session_unset();
header('Location: login.php');

?>