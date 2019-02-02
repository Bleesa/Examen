package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.service.Service;

@Controller
public class PeliculaServlet {


	private static final long serialVersionUID = 1L;

	@Autowired
	private Service service;

	@PostMapping(path = "/film")
	protected void filmAll() {
		service.selectAllPelicula();
	}

	@GetMapping(path = "film")
	protected String film() {

		return "film";
	}

}
