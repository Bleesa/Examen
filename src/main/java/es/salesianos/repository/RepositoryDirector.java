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
import es.salesianos.model.Director;


@Repository
public class RepositoryDirector {
	
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

	public void insertDirector(Director director) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO DIRECTOR (NAME)" +
					"VALUES (?)");
			preparedStatement.setString(1, director.getName());


			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error a la hora de INSERTAR " + e);
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}
	
	public List<Director> filterDirector(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Director> list = new ArrayList<Director>();
		try {
			preparedStatement = conn.prepareStatement(
					"SELECT DIRECTOR.NAME FROM (((ACTOR" + " INNER JOIN FILMACTOR ON FILMACTOR.CODACTOR = ACTOR.COD)"
							+ " INNER JOIN FILM ON FILM.COD = FILMACTOR.CODFILM)"
							+ " INNER JOIN DIRECTOR ON DIRECTOR.COD = FILM.CODOWNER) WHERE ACTOR.NAME = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Director directorfromDataBase = new Director();
				directorfromDataBase.setName(resultSet.getString(1));
				list.add(directorfromDataBase);
			}

		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public List<Director> searchAllDirectors() {
		List<Director> listDirectors = new ArrayList<Director>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			
			prepareStatement = conn.prepareStatement("SELECT * FROM DIRECTOR");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Director directorInDataBase = new Director();
				
				directorInDataBase.setCod(resultSet.getInt(1));
				directorInDataBase.setName(resultSet.getString(2));
			
				
				listDirectors.add(directorInDataBase);
			}
			
		} catch (SQLException e) {
			log.error("Error a la hora de SELECCIONAR DIRECTOR " + e);
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
			manager.close(conn);
		}

		return listDirectors;
	}
	
	public Director searchAndDeleteDirector(Integer codDirector) {
		Director ownerInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM DIRECTOR WHERE COD = ?");
			prepareStatement.setInt(1, codDirector);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error a la hora de BUSCAR PARA BORRAR" + e);
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return ownerInDatabase;
	}

}
