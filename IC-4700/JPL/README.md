Instalar JPL y ejecutar ejemplo en Ubuntu 14.10

Se supone que ya está instalado SWI Prolog y Oracle JDK 8

```
sudo apt-get install swi-prolog-java
export LD_LIBRARY_PATH="/usr/lib/swi-prolog/lib/amd64" 
javac -cp /usr/lib/swi-prolog/lib/jpl.jar JPLTest.java
java -cp /usr/lib/swi-prolog/lib/jpl.jar:. JPLTest
```