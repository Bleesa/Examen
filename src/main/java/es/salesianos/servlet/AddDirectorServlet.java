package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.Director;
import es.salesianos.model.assembler.ExamAssembler;
import es.salesianos.service.DirectorInterface;

/**
 * Servlet implementation class addOwnerServlet
 */

@Controller
public class AddDirectorServlet {

	@Autowired
	private DirectorInterface service;

	@Autowired
	private ExamAssembler assembler;

	@PostMapping(path = "/addDirector")
	public void addDirector(Director director) {

		service.addDirector(director);

	}


}
