package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.salesianos.model.Director;
import es.salesianos.service.DirectorInterface;

@Controller
public class DeleteDirectorServlet {
	
	@Autowired
	private DirectorInterface service;

	@GetMapping(path = "index")
	protected String deleteDirector(Director director) {
		service.deleteDirector(director);
		return "index";
	}


}
