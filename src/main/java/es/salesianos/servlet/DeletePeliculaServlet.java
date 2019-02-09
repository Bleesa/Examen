package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.salesianos.service.FilmInterface;

@Controller
public class DeletePeliculaServlet {
	
	@Autowired
	private FilmInterface service;

	@GetMapping(path = "index")
	protected String searchDeleteFilm() {

		return "index";
	}


}
