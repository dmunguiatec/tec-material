ic5701
======

Compiladores e Intérpretes

## GrammarParser

Parser de gramáticas libres de contexto. No detecta ni se recupera de errores.

Ejemplo de gramática soportada por este parser:

```
expr  -> term expr' ;
expr' -> '+' term expr' ;
expr' -> ε ;
term  -> fact term' ;
term' -> '*' fact term' ;
term' -> ε ;
fact  -> ID ;
fact  -> '(' expr ')' ;
```

Genera una lista de objetos `Rule`.

```
class Rule {
   Symbol leftHand;
   List<Symbol> rightHand;
}

class Symbol {
   String label;
   boolean terminal;
}
```

Ejemplo de uso:

```
import ac.tec.ic5701.grammarparser.*;

...

GParser parser = new GParser();
List<Rule> rules = parser.parse(new FileReader("expresiones.gramatica"));
System.out.println("rules = " + rules);
```