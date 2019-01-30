package es.salesianos.model;

import java.util.ArrayList;
import java.util.List;

public class Actor {
	private Integer cod;
	private String name;
	private Integer yearOfBirthday;
	private List<FilmActors> filmActor = new ArrayList<FilmActors>();

	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYearOfBirthday() {
		return yearOfBirthday;
	}
	public void setYearOfBirthday(Integer yearofbirthday) {
		this.yearOfBirthday = yearofbirthday;
	}
	public List<FilmActors> getFilmActor() {
		return filmActor;
	}
	public void setFilmActor(List<FilmActors> filmActor) {
		this.filmActor = filmActor;
	}

}
