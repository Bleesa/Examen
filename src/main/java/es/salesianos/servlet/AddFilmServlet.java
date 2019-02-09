package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Film;
import es.salesianos.model.assembler.ExamAssembler;
import es.salesianos.service.FilmInterface;

/**
 * Servlet implementation class addOwnerServlet
 */
@Controller
public class AddFilmServlet {

	@Autowired
	private FilmInterface service;

	@Autowired
	private ExamAssembler assembler;

	@PostMapping(path = "/addFilm")
	public void addFilm(Film film) {

		service.addFilm(film);

	}


}
