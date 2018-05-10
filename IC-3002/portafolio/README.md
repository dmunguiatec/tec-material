# Portafolios

## ¿Cómo empezar mi propio portafolios?

Esta guía presupone que se está trabajando en alguna distribución de Linux (en mi caso Ubuntu 16.04).

Verificar que `python3` está instalado en el sistema.

```bash
python3 --version
```

En mi caso el comando produce la siguiente salida.

```bash
$ python3 --version
Python 3.5.2
```

Si el comando produce un error es posible que `python3` no esté instalado. Para instalarlo utilice el siguiente comando.

```bash
sudo apt-get install python3
```

Verificar que `pip3` está instalado en el sistema.

```bash
pip3 --version
```

En mi caso el comando produce la siguiente salida.

```bash
$ pip3 --version
pip 8.1.1 from /usr/lib/python3/dist-packages (python 3.5)
```

Nuevamente si el comando produce un error debe instalar `pip3` manualmente. Para instalarlo utilice el siguiente comando.

```bash
$ wget https://bootstrap.pypa.io/get-pip.py
$ sudo -H python3 get-pip.py
```

Una vez verificado que se cuenta con las herramientas mínimas, el siguiente paso es instalar `virtualenv`.

```bash
$ sudo -H pip3 install virtualenv
```

Instalar también `virtualenvwrapper`.

```bash
$ sudo -H pip3 install virtualenvwrapper
```

Agregar las siguientes líneas al final del archivo `~/.profile`

```bash
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3
export VIRTUALENVWRAPPER_VIRTUALENV=/usr/local/bin/virtualenv
export WORKON_HOME=$HOME/.virtualenvs
export PROJECT_HOME=$HOME/dev
source /usr/local/bin/virtualenvwrapper.sh
```

Y refrescar la sesión del shell.

```bash
$ source ~/.profile
```

Cree un repositorio privado en gitlab llamado `ic3002_portafolio` y clónelo localmente. A partir de este punto la guía asume que ud está trabajando dentro del directorio de su proyecto `ic3002_portafolio`.

```bash
$ git clone git@gitlab.com:<su nombre de usuario aquí>/ic3002_portafolio.git
$ cd ic3002_portafolio
```

Cree un nuevo ambiente virtual para su portafolio dentro de su directorio de proyecto.

```bash
ic3002_portafolio$ mkvirtualenv portafolio
```

Instale `jupyter`.

```bash
$ pip3 install jupyter
```

Para iniciar el servidor de `jupyter`

```bash
$ jupyter notebook
```

