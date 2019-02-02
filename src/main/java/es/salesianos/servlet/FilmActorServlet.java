package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Film;
import es.salesianos.service.GeneralInterface;

/**
 * Servlet implementation class PeliculaActorServlet
 */

@Controller
public class FilmActorServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;


	@PostMapping(path = "/FilmActor")
	protected void addFilm(Film film) {

		service.addFilm(film);
	}

	@GetMapping(path = "FilmActor")
	protected String addFilmActor() {

		return "FilmActor";
	}




}
