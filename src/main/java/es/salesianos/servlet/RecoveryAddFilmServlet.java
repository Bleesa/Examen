package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Film;
import es.salesianos.service.ActorService;
import es.salesianos.service.GeneralInterface;

@Controller
public class RecoveryAddFilmServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;

	@Autowired
	private ActorService service2;

	@PostMapping(path = "/selectActor")
	protected void addFilm(Film film) {


		service.addFilm(film);

	}

	@GetMapping(path = "selectActor")
	protected String selectActor() {
		return "selectActor";
	}



}
