# ¿Cómo ejecutar el ejemplo?

## Ambiente de desarrollo

La siguiente documentación es válida para Ubuntu Linux (y posiblemente algunas de las distribuciones emparentadas con esta). Para instalar el ambiente de desarrollo que incluye `apache`, `mysql` y `php` debe seguir la guía en:

[https://help.ubuntu.com/community/ApacheMySQLPHP](https://help.ubuntu.com/community/ApacheMySQLPHP)

## Base de datos

+ Asegúrese de tener instalado el motor de base de datos `mysql`, preferiblemente las versión 5.5.

+ Crear la base de datos vacía. Abra la consola de mysql como el usuario `root` e ingrese los siguientes comandos:

```
create database ic6821_ejemplo2;
grant all privileges on ic6821_ejemplo2.* to 'ic6821'@'localhost' identified by 'ic6821';
exit
```

Restaure el dump `script.sql` en la nueva base de datos `ic6821_ejemplo2`:

```
mysql -u ic6821 --password=ic6821 ic6821_ejemplo2 < ic6821/ejemplo2/script.sql
```

## Instalar aplicación

Copie los archivos de código y recursos al servidor `apache`:

```
sudo cp -rf ejemplo2 /var/www ; sudo chmod 777 /var/www/ejemplo2/img
```