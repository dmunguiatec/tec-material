<?php
// initial config
header('Content-Type: text/html; charset=utf-8');
session_start();
ini_set('display_errors',1);  
error_reporting(E_ERROR | E_PARSE);

// default values
$username = "";
$password = "";

$empty_username = FALSE;
$empty_password = FALSE;

$username_exists = TRUE;
$valid_password = TRUE;

// is POST?
if (isset($_POST['submit'])) {

    // get POST parameters
    $username = $_POST["username"];
    $password = $_POST["password"];

    // username and password are required
    $empty_username = empty($username);
    $empty_password = empty($password);

    if (!$empty_username && !$empty_password) {
      // open db connection
      $connection = mysql_connect("localhost", "ic6821", "ic6821") or die("No se pudo obtener conexion a bd");
      mysql_select_db("ic6821_ejemplo2");

      // find password by username
      $find_pwd_by_usr = sprintf("select password from users where username = '%s'",
        mysql_real_escape_string($username));

      $result = mysql_query($find_pwd_by_usr);
      $result_count = mysql_num_rows($result);
      
      $username_exists = $result_count > 0;

      if ($username_exists) {
        // compare db password hash with parameter password hash
        $user = mysql_fetch_assoc($result);
        $valid_password = md5($password) == $user['password'];
      }

      mysql_close($connection);

      if ($username_exists && $valid_password) {
        // generate session token
        $_SESSION["auth_token"] = $username.','.md5($username."soy la llave de encripcion");
        header('Location: home.php');
        die();
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
      <h3>Ingrese sus credenciales</h3>
    </div>
    <div class="body">
      <form class="login-form" name="login-form" action="login.php" method="POST">
        <?php if (!$username_exists): ?>
        <span style="color:red">El nombre de usuario <b><?= $username ?></b> no está registrado.</span>
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
        <span style="color:red">Clave incorrecta.</span>
        <?php endif ?>
        <div>
          <label for="password">Clave</label>
          <div class="input">
            <input type="password" name='password' value="" id="password" />
            <?php if ($empty_password): ?>
            <span style="color:red">*</span>
            <?php endif ?>
          </div>
        </div>
        <div>
          <div class="input">
            <input type="submit" name="submit" value="Ingresar" id="submit" />
          </div>
        </div>
      </form>
    </div>
    <div>
      <div class="input">
        <p><strong>¿Todavía no tiene una cuenta?</strong></p>
        <p><a href="register.php">Regístrese</a></p>
      </div>
    </div>
  </div>
</body>
</html>