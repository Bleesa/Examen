package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.repository.RepositoryActor;
import es.salesianos.service.DirectorInterface;

@Controller
public class DirectorsList {
	
	@Autowired
	private DirectorInterface service;

	@Autowired
	private RepositoryActor repositoryActor;
	
	@PostMapping(path = "/addDirector")
	protected void listAllDirectors() {

		service.listAllDirectors();
	}
	


}
