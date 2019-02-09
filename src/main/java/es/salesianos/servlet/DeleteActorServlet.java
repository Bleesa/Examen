package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.salesianos.model.Actor;
import es.salesianos.service.ActorInterface;

@Controller
public class DeleteActorServlet {
	
	@Autowired
	private ActorInterface service;
	@GetMapping(path = "index")
	protected String deleteActor(Actor actor) {
		service.deleteActor(actor);
		return "index";

	}


}
