package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.FilmActors;
import es.salesianos.service.FilmInterface;

@Controller
public class FillPeliculaActorServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmInterface service;

	@PostMapping(path = "/fillFilmActor")
	protected void insertFilmActor(FilmActors filmActor) {

		service.insertFilmActor(filmActor);

	}

	@GetMapping(path = "fillFilmActor")
	protected String insertFilmWithActor() {

		return "fillFilmActor";

	}



}
