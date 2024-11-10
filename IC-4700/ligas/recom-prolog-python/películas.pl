% Películas
película('Spider-Man: No Way Home', action, 8.3).
película('Dune', sci_fi, 8.1).
película('Everything Everywhere All at Once', sci_fi, 8.0).
película('The Batman', action, 7.9).
película('Top Gun: Maverick', action, 8.6).
película('Parasite', thriller, 8.6).
película('Joker', drama, 8.4).
película('Avatar: The Way of Water', sci_fi, 7.6).
película('Black Panther: Wakanda Forever', action, 7.2).
película('Oppenheimer', drama, 8.6).

% Preferencias
preferencia(alex, sci_fi).
preferencia(alex, drama).
preferencia(maria, action).
preferencia(maria, thriller).

recomendar(Usuario, Película) :-
    preferencia(Usuario, Género),
    película(Película, Género, Calificación),
    Calificación >= 8.0.
