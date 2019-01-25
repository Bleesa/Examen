package es.salesianos.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CloseStatement {

	private void close(PreparedStatement prepareStatement) {

		try {
			prepareStatement.close();
		} catch (SQLException e) {

			e.printStackTrace();

			throw new RuntimeException(e);
		}
	}
}