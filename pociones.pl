uno_es_otro_no(violeta,azul).
uno_es_otro_no(amarillo,rojo).
uno_es_otro_no(azul,anaranjado).

alguno_no_es(violeta,amarillo).
alguno_no_es(rojo,anaranjado).
alguno_no_es(verde,azul).

noes(rojo).

venenosos(_,Y):-noes(X),uno_es_otro_no(X,Y);noes(X),uno_es_otro_no(Y,X).

novenenosos(_,_,Z):-
venenosos(_,Y),alguno_no_es(Z,Y);venenosos(_,Y),alguno_no_es(Y,Z).

solucion(_,Y,_):-venenosos(_,Y);novenenosos(_,_,Z),uno_es_otro_no(Z,Y);novenenosos(_,_,Z),uno_es_otro_no(Y,Z).
