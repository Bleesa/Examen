package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Actor;
import es.salesianos.service.GeneralInterface;

@Controller
public class ActorsList {

	@Autowired
	private GeneralInterface service;
	
	@Autowired
	private GeneralInterface repositoryActor;
	
	@PostMapping(path = "/addActor")
	protected void addActor(Actor actor) {

		service.addActor(actor);

	}
	
	

}
