/*
 -------------------------------------------------------------------------------
 Nombre:		eliza.pl
 Autor: 		(Sterling & Shapiro, 1994, p. 275)
 Licencia:		Apache 2.0
  
 Versión simplificada del programa clásico de IA "Eliza", un simulador 
 rudimentario de conversaciones. En el contexto de la conversación, el 
 programa juega el rol de una psicóloga que escucha los problemas personales
 del usuario. Weizenbaum, el autor de este programa utilizó los resultados
 de este experimento para estudiar la IA desde la perspectiva de la ética y
 eventualmente tomó una posición crítica hacia esta disciplina.
 (ORegan, 2016, p. 259)
 -------------------------------------------------------------------------------
*/

important(father).
important(mother).
important(son).
important(sister).
important(brother).
important(daughter).

%% eliza(+Input, -Output) is det
% 
% [¿Qué hace?] 

eliza(Input, Output) :-
	pattern(Stimulus, Response),
	match(Stimulus, Dictionary, Input),
	match(Response, Dictionary, Output),
	!.

%% match(+Pattern, +Dictionary, -Target)
%
% [¿Qué hace?]

match([Placeholder|Pattern], Dictionary, Target) :-
	integer(Placeholder),
	lookup(Placeholder, Dictionary, LeftTarget),
	append(LeftTarget, RightTarget, Target),
	match(Pattern, Dictionary, RightTarget).

match([Word|Pattern], Dictionary, [Word|Target]) :-
	atom(Word),
	match(Pattern, Dictionary, Target).

match([], _, []).

%% lookup(+Key, +Dictionary, -Value)
%
% [¿Qué hace?]

lookup(Key, [(Key, Value)|_], Value).
lookup(Key, [(OtherKey, _)|Dictionary], Value) :-
	Key \= OtherKey,
	lookup(Key, Dictionary, Value).

%% pattern(+Stimulus, -Response)
% 
% [¿Qué hace?]
pattern(['I', am, 1], ['How', long, have, you, been, 1, ?]).
pattern([1, you, 2, me], ['What', makes, you, think, 'I', 2, you, ?]).
pattern(['I', like, 1], ['Does', anyone, else, in, your, family, like, 1, ?]).
pattern(['I', feel, 1], ['Do', you, often, feel, that, way, ?]).
pattern([1, Person, 2], ['Please', tell, me, more, about, Person, .]) :-
	important(Person).
pattern([1], ['Please', go, on, .]).
