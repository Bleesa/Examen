package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.salesianos.service.GeneralInterface;

@Controller
public class DeleteDirectorServlet {
	
	@Autowired
	private GeneralInterface service;

	@GetMapping(path = "index")
	protected String deleteDirector() {

		return "index";
	}


}
