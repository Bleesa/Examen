package es.salesianos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseResultSet {
	private void close(ResultSet resultSet) {

		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException(e);

		}
	}
}