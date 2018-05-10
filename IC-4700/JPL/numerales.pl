numeral(0).
numeral(sucesor(X)) :- numeral(X).

suma(0, B, B).
suma(sucesor(A), B, sucesor(Resultado)) :- suma(A, B, Resultado).

