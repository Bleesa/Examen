package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.repository.RepositoryActor;
import es.salesianos.service.GeneralInterface;

@Controller
public class FilmsList {
	
	@Autowired
	private GeneralInterface service;

	@Autowired
	private RepositoryActor repositoryActor;
	
	@PostMapping(path = "/addFilm")
	protected void listFilm() {

		service.listAllFilms();
	}


}
