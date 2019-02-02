package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;

public interface GeneralInterface {

	void selectAllActor();

	List<Actor> listAllActors();

	void searchAndDeleteActor(Integer codActor);

	void addActor(Actor actor);

	List<Actor> filterAllActor(int beginDate, int endDate);

	Actor filterAllDirector(String name);

	void addDirector(Director director);

	void addFilm(Film film);

	FilmActors filterAllPeliculaActor(String role);

	void searchAndDeleteDirector(int parseInt);

	void searchAndDeleteFilm(int i);

	List<Director> listAllDirectors();

	List<Film> listAllFilms();

	void insertFilmActor(FilmActors filmActor);

}
