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
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
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
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
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
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return ownerInDatabase;
	}

}
