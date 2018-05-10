<?php
header('Content-Type: text/html; charset=utf-8');
session_start();
ini_set('display_errors',1);  
error_reporting(E_ERROR | E_PARSE);

$username = "";
$userId = -1;

// validate session token
if ($_SESSION['auth_token']) {
    list($username, $cookie_hash) = explode(',', $_SESSION['auth_token']);
    if (md5($username."soy la llave de encripcion") != $cookie_hash) {
        header('HTTP/1.1 403 Forbidden');
        die();
    }
} else {
   header('HTTP/1.1 403 Forbidden');
   die();
}

$connection = mysql_connect("localhost", "ic6821", "ic6821") or die("No se pudo obtener conexion a bd");
mysql_select_db("ic6821_ejemplo2");

// load user data
$find_user_by_username = sprintf("select * from users where username = '%s'", mysql_real_escape_string($username));
$result = mysql_query($find_user_by_username);
$user = mysql_fetch_assoc($result);

$user_thumbnail_location = "img/".$user['id'];
$thumbnail_location = file_exists($user_thumbnail_location) ? $user_thumbnail_location : "img/thumbnail_placeholder.png";

mysql_close($connection);

if (isset($_POST['submit-thumbnail'])) {
   move_uploaded_file($_FILES['thumbnail']['tmp_name'], 'img/'.$user['id']);
   header("Location: home.php");
   die();
}  

if (isset($_POST['submit-profile'])) {
   $connection = mysql_connect("localhost", "ic6821", "ic6821") or die("No se pudo obtener conexion a bd");
   mysql_select_db("ic6821_ejemplo2");

   $first_name = $_POST['first_name'];
   $last_name = $_POST['last_name'];
   $email = $_POST['email'];

   $update_user_profile = sprintf("update users set first_name = '%s', last_name='%s', email='%s' where id=%s",
      mysql_real_escape_string($first_name),
      mysql_real_escape_string($last_name),
      mysql_real_escape_string($email),
      $user['id']);

   mysql_query($update_user_profile);
   mysql_close($connection);

   header("Location: home.php");
   die();
}

?>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>Ejemplo 2</title>
</head>
<body>
   <div><h3>Bienvenido, <?= $username ?></h3></div>
   <div>
   <ul>
      <li><a href="logout.php">Salir</a></li>
   </ul>
   </div>
   <div>
      <img src="<?= $thumbnail_location ?>" style="width: 200px; height: 200px"/>
      <form id='thumbnail-form' name='thumbnail-form' action='home.php' method='POST' enctype="multipart/form-data">
        <div>
          <label for="thumbnail">Cambiar foto de perfil</label>
          <div class="input">
            <input type="file" name='thumbnail' id="thumbnail" />
          </div>
        </div>
        <div>
          <div class="input">
            <input type="submit" name="submit-thumbnail" value="Guardar" id="submit-thumbnail" />
          </div>
        </div>
      </form>
   </div>
   <div>
      <form class="profile-form" name="profile-form" action="home.php" method="POST">
        <div>
          <label for="first_name">Nombre</label>
          <div class="input">
            <input type="text" name='first_name' value="<?= $user['first_name'] ?>" id="first_name" />
          </div>
        </div>
        <div>
          <label for="last_name">Apellido</label>
          <div class="input">
            <input type="text" name='last_name' value="<?= $user['last_name'] ?>" id="last_name" />
          </div>
        </div>
        <div>
          <label for="email">Correo electrónico</label>
          <div class="input">
            <input type="text" name='email' value="<?= $user['email'] ?>" id="email" />
          </div>
        </div>
        <div>
          <div class="input">
            <input type="submit" name="submit-profile" value="Ingresar" id="submit-profile" />
          </div>
        </div>
      </form>
   </div>
</body>
</html>