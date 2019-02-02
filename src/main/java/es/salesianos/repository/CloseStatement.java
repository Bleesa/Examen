package es.salesianos.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

@Repository
public class CloseStatement {

	private static final Logger log = LogManager.addLogger(CloseStatement.class);

	private void close(PreparedStatement prepareStatement) {

		try {
			prepareStatement.close();
		} catch (SQLException e) {

			log.error("Error a la hora de CERRAR " + e);
			e.printStackTrace();

			throw new RuntimeException(e);
		}
	}
}