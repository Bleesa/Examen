package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;

public interface DirectorInterface {
	Actor filterAllDirector(String name);

	void addDirector(Director director);

	void searchAndDeleteDirector(int parseInt);

	List<Director> listAllDirectors();

	public void deleteDirector(Director director);

}
