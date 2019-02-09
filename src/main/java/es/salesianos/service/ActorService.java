package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;
import es.salesianos.repository.RepositoryActor;

@Service
public class ActorService implements GeneralInterface {

	private RepositoryActor repositoryActor = new RepositoryActor();

	public RepositoryActor getRepository() {

		return repositoryActor;
	}

	public void setRepository(RepositoryActor repositoryActor) {

		this.repositoryActor = repositoryActor;
	}
	
	public List<Actor> listAllActors() {

		return repositoryActor.searchAllActors();
	}

	public void searchAndDeleteActor(Integer codActor) {

		repositoryActor.DeleteActorById(codActor);
	}

	public void addActor(Actor actor) {

		repositoryActor.insertActor(actor);
	}

	public List<Actor> filterAllActor(int beginDate, int endDate) {

		return repositoryActor.filterByYearOfDateBetween(beginDate, endDate);
	}

	public List<Actor> selectAllActor() {

		return repositoryActor.selectAllActor();
	}

	public Actor filterAllDirector(String name) {

		return repositoryActor.filterAllActor(name);
	}

	@Override
	public void selectAllActor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDirector(Director director) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFilm(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public FilmActors filterAllPeliculaActor(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void searchAndDeleteDirector(int parseInt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchAndDeleteFilm(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Director> listAllDirectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Film> listAllFilms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertFilmActor(FilmActors filmActor) {
		// TODO Auto-generated method stub

	}
}
