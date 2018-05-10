grammar Grammar;

gram: grule+ ;
grule: leftHand '->' rightHand ';' ;
leftHand: NOTERMINAL ;
rightHand: (TERMINAL|NOTERMINAL)+ ;

TERMINAL: '\''(.)*?'\'' | [A-Z]+ | EPSILON;
NOTERMINAL: [a-z]+('\'')? ;

fragment
EPSILON: 'ε';

WS : [\t\r\n ] -> skip;