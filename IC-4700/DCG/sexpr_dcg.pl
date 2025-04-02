sexpr --> valor | invocación.
valor --> átomo | número.

invocación --> ['('], functor, argumento, argumento, [')'].
argumento --> valor | sexpr.

functor --> [+] | [*] | [=] | [and] | [or].

átomo --> [x] | [y] | [a] | [b].
número --> [1] | [0].

% sexpr(['(', +, 1, '(', *, x, 0, ')', ')'], []).
