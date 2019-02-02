package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.service.GeneralInterface;

@Controller
public class ActorServlet {


	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;

	@PostMapping(path = "/actor")
	protected void ActorList() {

		service.listAllActors();
	}

	@GetMapping(path = "actor")
	public String listActor() {
		return "actor";
	}



}
