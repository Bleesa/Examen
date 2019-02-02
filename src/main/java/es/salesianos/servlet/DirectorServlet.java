package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.service.GeneralInterface;

@Controller
public class DirectorServlet {


	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;


	@PostMapping(path = "/director")
	protected void mostrarDirector() {
		service.getClass();
	}

	@GetMapping(path = "director")
	protected String listDirector() {
		return "director";
	}




}
