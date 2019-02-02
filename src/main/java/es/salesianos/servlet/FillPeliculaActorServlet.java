package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.FilmActors;
import es.salesianos.service.GeneralInterface;

@Controller
public class FillPeliculaActorServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;

	@PostMapping(path = "/FillFilmActor")
	protected void insertFilmActor(FilmActors filmActor) {

		service.insertFilmActor(filmActor);

	}

	@GetMapping(path = "FillFilmActor")
	protected String insertFilmWithActor() {

		return "FillFilmActor";

	}



}
