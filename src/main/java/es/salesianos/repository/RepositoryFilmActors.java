package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Actor;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;


@Repository
public class RepositoryFilmActors {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	private static final Logger log = LogManager.addLogger(RepositoryActor.class);

	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			log.error("Error a la hora de CERRAR " + e);
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
			log.error("Error a la hora de insertar " + e);
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

				FilmActors filmActorFromDataBase = new FilmActors();

				filmActorFromDataBase.setCache(resultSet.getInt(1));
				filmActorFromDataBase.setRole(resultSet.getString(2));
				filmActorFromDataBase.setCodActor(resultSet.getInt(3));
				filmActorFromDataBase.setCodFilm(resultSet.getInt(4));
				filmActor = filmActorFromDataBase;
				preparedStatement.close();
			}
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor where cod=filmActor.getCodActor()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorFromDataBase = new Actor();
				actorFromDataBase.setName(resultSet.getString(2));
				actorFromDataBase.setYearOfBirthday(resultSet.getInt(3));
				filmActor.setActor(actorFromDataBase);
				preparedStatement.close();
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILM where cod= + filmActor.getCodFilm()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Film filmfromDataBase = new Film();
				filmfromDataBase.setCod(resultSet.getInt(1));
				filmfromDataBase.setTitle(resultSet.getString(2));
				filmfromDataBase.setCodDirector(resultSet.getInt(3));
				filmActor.setFilm(filmfromDataBase);
			}

		} catch (SQLException e) {
			log.error("Error  " + e);
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return filmActor;
	}

}
