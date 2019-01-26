package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Actor;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;



public class RepositoryFilmActors {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();



	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

		
	public void insertFilmActors(FilmActors filmActor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO FILMACTOR (cache, role, codActor, codFilm) VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, filmActor.getCache());
			preparedStatement.setString(2, filmActor.getRole());
			preparedStatement.setInt(3, filmActor.getCodActor());
			preparedStatement.setInt(4, filmActor.getCodFilm());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}

	}

	public FilmActors filterAllFilmActor(String role) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		FilmActors filmActor = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM FILMACTOR WHERE ROLE = (?)");
			preparedStatement.setString(1, role);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				FilmActors filmActorfromDataBase = new FilmActors();

				filmActorfromDataBase.setCache(resultSet.getInt(1));
				filmActorfromDataBase.setRole(resultSet.getString(2));
				filmActorfromDataBase.setCodActor(resultSet.getInt(3));
				filmActorfromDataBase.setCodFilm(resultSet.getInt(4));
				filmActor = filmActorfromDataBase;
				preparedStatement.close();
			}
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor where cod=" + filmActor.getCodActor());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setName(resultSet.getString(2));
				actorfromDataBase.setYearOfBirthday(resultSet.getInt(3));
				filmActor.setActor(actorfromDataBase);
				preparedStatement.close();
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILM where cod=" + filmActor.getCodFilm());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Film filmfromDataBase = new Film();
				filmfromDataBase.setCod(resultSet.getInt(1));
				filmfromDataBase.setTitle(resultSet.getString(2));
				filmfromDataBase.setCodDirector(resultSet.getInt(3));
				filmActor.setFilm(filmfromDataBase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return filmActor;
	}

}
