package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Actor;
import es.salesianos.service.GeneralInterface;

@Controller
public class ActorSeacherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;

	@PostMapping(path = "/ActorSearcher")
	protected void seacherActor(Actor actor) {

		service.selectAllActor();
	}

	@GetMapping(path = "ActorSearcher")
	public String getPage() {
		return "ActorSeacher";
	}



}