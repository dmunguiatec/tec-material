padre(bardock, goku).
padre(oxsatan, milk).
padre(goku, gohan).
padre(goku, goten).
padre(gohan, pam).
madre(milk, gohan).
madre(milk, goten).
madre(videl, pam).
padre_familia(X, Y) :- madre(X, Y); padre(X, Y).
ancestro(X, Y) :- padre_familia(X, Y).
ancestro(X, Y) :- padre_familia(X, T), ancestro(T, Y).