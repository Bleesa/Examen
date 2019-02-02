package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Film;


@Repository
public class RepositoryFilm {
	
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
	
	public void insertFilm(Film film) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO FILM (TITTLE,CODOWNER)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, film.getTitle());
			preparedStatement.setInt(2, film.getCodDirector());


			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}

	public List<Film> searchAllFilms() {
		List<Film> listFilms = new ArrayList<Film>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			
			prepareStatement = conn.prepareStatement("SELECT * FROM FILM");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Film filmInDataBase = new Film();
				
				filmInDataBase.setCod(resultSet.getInt(1));
				filmInDataBase.setTitle(resultSet.getString(2));
				filmInDataBase.setCodDirector(resultSet.getInt(3));

				listFilms.add(filmInDataBase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}

		return listFilms;
	}

	public Film searchAndDeleteFilm(Integer codFilm) {
		Film ownerInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM FILM WHERE COD = ?");
			prepareStatement.setInt(1, codFilm);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return ownerInDatabase;
	}
	

}
