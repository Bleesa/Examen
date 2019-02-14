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
import es.salesianos.model.DtoActorFilm;
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

	public DtoActorFilm filterAllFilmActor(String role) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		DtoActorFilm filmActor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT TITTLE, NAME, YEAROFBIRTHDATE" + " FROM ((FILMACTOR"
					+ " INNER JOIN FILM ON FILM.COD = FILMACTOR.CODFILM)"
					+ " INNER JOIN ACTOR ON ACTOR.COD = FILMACTOR.CODACTOR)" + " WHERE FILMACTOR.ROLE = (?)");

			preparedStatement.setString(1, role);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				DtoActorFilm dtofromDataBase = new DtoActorFilm();
				dtofromDataBase.setTitle(resultSet.getString(1));
				dtofromDataBase.setName(resultSet.getString(2));
				dtofromDataBase.setYear(resultSet.getInt(3));
				filmActor = dtofromDataBase;
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
