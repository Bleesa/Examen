package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;

public interface FilmInterface {
	void addFilm(Film film);

	FilmActors filterAllPeliculaActor(String role);

	void searchAndDeleteFilm(int i);

	List<Film> listAllFilms();

	void insertFilmActor(FilmActors filmActor);


}
