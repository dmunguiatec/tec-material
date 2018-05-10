# Eliza

Versión simplificada del programa clásico de IA "Eliza", un simulador 
rudimentario de conversaciones. En el contexto de la conversación, el 
programa juega el rol de una psicóloga que escucha los problemas personales
del usuario. Weizenbaum, el autor de este programa utilizó los resultados
de este experimento para estudiar la IA desde la perspectiva de la ética y
eventualmente tomó una posición crítica hacia esta disciplina.
(O'Regan, 2016, p. 259)

Ejemplo de uso:

```
?- [eliza].

?- eliza(['I', am, unhappy], R).
R = ['How', long, have, you, been, unhappy, ?].

?- eliza(['Six', months, ., 'Can', you, help, me, ?], R).
R = ['Please', go, on, '.'].

?- eliza(['Can', you, help, me], R).
R = ['What', makes, you, think, 'I', help, you, ?].

?- eliza(['You', remind, me, of, my, mother], R).
R = ['Please', tell, me, more, about, mother, '.'].
```