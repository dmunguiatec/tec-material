# DCG

Ejemplo de uso de DCG (definite clause grammar) en Prolog.

Uso:

```
$> swipl dcg.pl

?- oracion([el,hombre,pasea,un,perro],[]).
true .

?- oracion([el,hombre,pasea,un,pasea],[]).
false.

?- oracion(O,[]).
O = [el, hombre, observa, el, hombre] ;
O = [el, hombre, observa, el, perro] ;
O = [el, hombre, observa, un, hombre] ;
O = [el, hombre, observa, un, perro] ;
O = [el, hombre, pasea, el, hombre] . 
```
