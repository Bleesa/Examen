package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.service.GeneralInterface;

@Controller
public class CharacterSearcherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GeneralInterface service;

	@PostMapping(path = "/characterSearcher.jsp")
	protected void characSeacher(String role) {

		service.filterAllPeliculaActor(role);
	}

	@GetMapping(path = "characterSearcher")
	protected String characterSearcher() {
		return "characterSearcher";
	}


}
