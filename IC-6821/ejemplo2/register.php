<?php
header('Content-Type: text/html; charset=utf-8');
session_start();
ini_set('display_errors',1);  
error_reporting(E_ERROR | E_PARSE);

$username = "";
$password = "";
$password_confirm = "";

$valid_password = TRUE;
$valid_username = TRUE;

$empty_username = FALSE;
$empty_password = FALSE;
$empty_password_confirm = FALSE;

if (isset($_POST['submit'])) {
  $username = $_POST['username'];
  $password = $_POST['password'];
  $password_confirm = $_POST['password_confirm'];

  $empty_username = empty($username);
  $empty_password = empty($password);
  $empty_password_confirm = empty($password_confirm);

  if (!$empty_username && !$empty_password && !$empty_password_confirm) {
    // validate that password and confirm matches
    $valid_password = $password == $password_confirm;

    if ($valid_password) {
      $connection = mysql_connect("localhost", "ic6821", "ic6821") or die("No se pudo obtener conexion a bd");
      mysql_select_db("ic6821_ejemplo2");

      // validate that username is unique
      $find_user_by_username = sprintf("select id from users where username = '%s'", mysql_real_escape_string($username));
      $result = mysql_query($find_user_by_username);
      $result_count = mysql_num_rows($result);

      $valid_username = $result_count == 0;

      if ($valid_username) {
        // create new user
        $insert_new_user = sprintf("insert into users(username, password) values ('%s', '%s')", 
          mysql_real_escape_string($username),
          mysql_real_escape_string(md5($password)));

        mysql_query($insert_new_user) or die ("Error insertando nuevo usuario en bd");

        $userId = mysql_insert_id();
      }

      mysql_close($connection);

      if ($valid_password && $valid_username) {
        // authenticate
        $_SESSION["auth_token"] = $username.','.md5($username."soy la llave de encripcion");
        header('Location: home.php');
        die();
      }
    } 
  }
}
?>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>Ejemplo 2</title>
</head>
<body>
  <div>
    <div class="header">
      <h3>Registro de nueva cuenta</h3>
    </div>
    <div class="body">
      <form class="register-form" name="register-form" action="register.php" method="POST">
        <?php if (!$valid_username): ?>
        <span style="color:red">El nombre de usuario <b><?= $username ?></b> ya está tomado, ingrese uno diferente.</span>
        <?php endif ?>
        <div>
          <label for="username">Nombre de usuario</label>
          <div class="input">
            <input type="text" name='username' value="<?= $username ?>" id="username" />
            <?php if ($empty_username): ?>
            <span style="color:red">*</span>
            <?php endif ?>
          </div>
        </div>
        <?php if (!$valid_password): ?>
        <span style="color:red">La clave y su confirmación no coinciden.</span>
        <?php endif ?>
        <div>
          <label for="password">Clave</label>
          <div class="input">
            <input type="password" name='password' value="<?= $password ?>" id="password" />
            <?php if ($empty_password): ?>
            <span style="color:red">*</span>
            <?php endif ?>
          </div>
        </div>
        <div>
          <label for="password">Confirmar clave</label>
          <div class="input">
            <input type="password" name='password_confirm' value="<?= $password_confirm ?>" id="password_confirm" />
            <?php if ($empty_password_confirm): ?>
            <span style="color:red">*</span>
            <?php endif ?>
          </div>
        </div>
        <div>
          <div class="input">
            <input type="submit" name="submit" value="Crear cuenta" id="submit" />
          </div>
        </div>
      </form>
    </div>
  </div>
</body>
</html>