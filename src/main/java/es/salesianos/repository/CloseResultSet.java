package es.salesianos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CloseResultSet {

	private static final Logger log = LogManager.addLogger(ClaseResultSet.class);

	private void close(ResultSet resultSet) {

		try {
			resultSet.close();
		} catch (SQLException e) {
			log.error("Error a la hora de CERRAR " + e);
			e.printStackTrace();

			throw new RuntimeException(e);

		}
	}
}