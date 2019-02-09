package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;

public interface ActorInterface {

	void selectAllActor();

	List<Actor> listAllActors();

	void searchAndDeleteActor(Integer codActor);

	void addActor(Actor actor);

	List<Actor> filterAllActor(int beginDate, int endDate);

	Actor filterAllDirector(String name);

	void deleteActor(Actor actor);

}
