package es.salesianos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import es.salesianos.model.FilmActor;
import es.salesianos.model.ActorFilmDTO;
import es.salesianos.model.Actor;
import es.salesianos.model.Film;
import es.salesianos.service.IActorService;
import es.salesianos.service.IFIlmActorService;
import es.salesianos.service.IFIlmService;

@Controller
public class FilmActorController  {

	@Autowired
	IFIlmService filmService;
	@Autowired
	IActorService actorService;
	@Autowired
	IFIlmActorService filmActorService;

	@GetMapping("/linkActors")
	protected ModelAndView getFilmsToLink() {
		List<Film> listAllFilm = filmService.selectAllFilm();
		ModelAndView model = new ModelAndView("linkActors");
		model.addObject("listAllFilm", listAllFilm);
		return model;
	}

	@GetMapping("/chooseActor")
	protected ModelAndView chooseActor(@RequestParam Integer filmCod) {
		List<Actor> listAllActor = actorService.listAllActor();
		ModelAndView model = new ModelAndView("chooseActor");
		model.addObject("listAllActor", listAllActor);
		model.addObject("filmCod", filmCod);
		return model;
	}

	@GetMapping("/recoveryFilmActor")
	protected ModelAndView recoveryData(@RequestParam Integer filmCod, @RequestParam Integer actorCod) {
		ModelAndView model = new ModelAndView("fillFilmActor");
		model.addObject("filmCod", filmCod);
		model.addObject("actorCod", actorCod);
		return model;
	}

	@PostMapping("/fillFilmActor")
	protected ModelAndView fillFilmActor(FilmActor filmActor) {
		filmActorService.insert(filmActor);
		return getFilmsToLink();
	}
	
	@GetMapping("/searchRole")
	protected String searchRole() {
		return "/searchRole";
	}

	@PostMapping("/searchRole")
	protected ModelAndView filterByDirector(String role) {
		ActorFilmDTO selectedActorFilm = filmActorService.filterActorFilm(role);
		ModelAndView model = new ModelAndView("searchRole");
		model.addObject("selectedActorFilm", selectedActorFilm);
		return model;
	}
}