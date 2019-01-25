package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;

public class ExamAssembler {

	public static Actor assembleActorFrom(HttpServletRequest req) {
		Actor actor = new Actor();
		String name=req.getParameter("NAME");
		Integer yearofbirthday=Integer.parseInt(req.getParameter("YEAROFBIRTHDAY"));
		actor.setName(name);

		actor.setYearOfBirthday(yearofbirthday);

		return actor;
	}
	
	public static Film assembleFilmFrom(HttpServletRequest req) {

		Film film = new Film();
		String titulo=req.getParameter("title");
		Integer codowner=Integer.parseInt(req.getParameter("CODOWNER"));

		film.setTitle(titulo);
		film.setCodDirector(codowner);

		return film;
	}
	
	
	public static Director assembleDirectorFrom(HttpServletRequest req) {
		Director director = new Director();
		String NAME=req.getParameter("NAME");
		director.setName(NAME);

		return director;
	}

	public static FilmActors assembleFilmActorFrom(HttpServletRequest req) {
		FilmActors FilmActor = new FilmActors();
		String codFilm = req.getParameter("codPelicula");
		String codActor = req.getParameter("codActor");
		String cache = req.getParameter("cache");
		String role = req.getParameter("role");

		FilmActor.setCodFilm(Integer.parseInt(codFilm));
		FilmActor.setCodActor(Integer.parseInt(codActor));
		FilmActor.setCache(Integer.parseInt(cache));
		FilmActor.setRole(role);

		return FilmActor;
	}

	public Actor assembleActorFromRequest(HttpServletRequest req) {
		return ExamAssembler.assembleActorFrom(req);
	}

	public Film assembleFilmFromRequest(HttpServletRequest req) {
		return ExamAssembler.assembleFilmFrom(req);
	}

	public Director assembleDirectorFromRequest(HttpServletRequest req) {
		return ExamAssembler.assembleDirectorFrom(req);
	}
}