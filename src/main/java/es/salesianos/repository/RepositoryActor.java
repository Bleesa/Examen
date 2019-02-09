package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;

@Repository
public class RepositoryActor {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();

	private static final Logger log = LogManager.addLogger(RepositoryActor.class);




	public void insertActor(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO ACTOR (NAME,YEAROFBIRTHDATE)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, actor.getName());
			preparedStatement.setInt(2, actor.getYearOfBirthday());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error a la hora de insertar un actor " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}

	public List<Actor> searchAllActors() {

		List<Actor> listOwners = new ArrayList<Actor>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM ACTOR");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorInDataBase = new Actor();
				
				actorInDataBase.setCod(resultSet.getInt(1));
				actorInDataBase.setName(resultSet.getString(2));
				actorInDataBase.setYearOfBirthday(resultSet.getInt(3));
			
				
				listOwners.add(actorInDataBase);
			}
			
		} catch (SQLException e) {
			log.error("Error a la hora de BUSCAR un actor " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}

		return listOwners;
	}

	public Actor DeleteActorById(Integer codActor) {
		Actor ownerInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM ACTOR WHERE COD = ?");
			prepareStatement.setInt(1, codActor);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error a la hora de eliminar un actor " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return ownerInDatabase;
	}
	

	public List<Actor> filterByYearOfDateBetween(int beginDate, int endDate) {

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();

		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR WHERE yearOfBirthDate BETWEEN (?) AND (?)");
			preparedStatement.setInt(1, beginDate);
			preparedStatement.setInt(2, endDate);

			System.out.println(beginDate);
			System.out.println(endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearOfBirthday(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			log.error("Error a la hora DE BUSCAR POR FECHAS  " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}
	
	public List<Actor> selectAllActor() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearOfBirthday(resultSet.getInt(3));
				list.add(actor);
			}
			
		} catch (SQLException e) {
			log.error("Error a la hora de SELECCIONAR " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public Actor filterAllActor(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Actor actor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor WHERE name = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setCod(resultSet.getInt(1));
				actorfromDataBase.setName(resultSet.getString(2));
				actorfromDataBase.setYearOfBirthday(resultSet.getInt(3));
				actor = actorfromDataBase;
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILMACTOR where codactor= actor.getCodActor()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FilmActors peliculaActorfromDataBase = new FilmActors();
				peliculaActorfromDataBase.setCodFilm(resultSet.getInt(4));
				actor.getFilmActor().add(peliculaActorfromDataBase);
			}

			int index = 0;
			for (FilmActors peliculaActor : actor.getFilmActor()) {

				preparedStatement = conn
						.prepareStatement("SELECT * FROM FILM where cod= peliculaActor.getCodFilm()");
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Film peliculafromDataBase = new Film();
					peliculafromDataBase.setTitle(resultSet.getString(2));
					peliculafromDataBase.setCodDirector(resultSet.getInt(3));
					actor.getFilmActor().get(index).setFilm(peliculafromDataBase);
				}
				index++;
			}
			index = 0;
			for (FilmActors peliculaActor : actor.getFilmActor()) {
				preparedStatement = conn.prepareStatement(
						"SELECT * FROM DIRECTOR where COD=peliculaActor.getFilm().getCodDirector()");
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Director directorfromDataBase = new Director();
					directorfromDataBase.setName(resultSet.getString(2));
					actor.getFilmActor().get(index).getFilm().setDirector(directorfromDataBase);
				}
				index++;
			}

		} catch (SQLException e) {
			log.error("Error a la hora de FILTRAR " + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return actor;

	}

}
