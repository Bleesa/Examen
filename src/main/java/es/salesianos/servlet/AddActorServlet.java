package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Actor;
import es.salesianos.service.ActorInterface;

/**
 * Servlet implementation class addOwnerServlet
 */

@Controller
public class AddActorServlet {

	@Autowired
	private ActorInterface service;

	@Autowired
	private ActorInterface assembler;

	@PostMapping(path = "/addActor")
	protected void addActor(Actor actor) {

		service.addActor(actor);
	}
}
