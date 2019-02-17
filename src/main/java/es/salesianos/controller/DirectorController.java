package es.salesianos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.model.Director;
import es.salesianos.service.IDirectorService;

@Controller
public class DirectorController {

	@Autowired
	private IDirectorService service;

	@PostMapping("/insertDirector")
	protected ModelAndView insertDirector(Director director) {
		service.insert(director);
		return loadList();
	}

	@GetMapping("/deleteDirector")
	protected ModelAndView deleteDirector(@RequestParam Integer cod) {
		if (cod != null) {
			service.delete(cod);
		}
		return loadList();
	}

	@GetMapping("/director")
	private ModelAndView loadList() {
		List<Director> listAllDirector = service.listAllDirector();
		ModelAndView model = new ModelAndView("director");
		model.addObject("listAllDirectors", listAllDirector);
		return model;
	}
	
	@GetMapping("/searchDirector")
	protected String searchDirector() {
		return "/searchDirector";
	}

	@PostMapping("/searchDirector")
	protected ModelAndView filterByDirector(String name) {
		List<Director> listFilterDirector = service.filterDirector(name);
		ModelAndView model = new ModelAndView("searchDirector");
		model.addObject("listFilterDirector", listFilterDirector);
		return model;
	}

}