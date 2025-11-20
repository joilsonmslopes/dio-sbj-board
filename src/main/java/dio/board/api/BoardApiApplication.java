package dio.board.api;

import dio.board.api.persistence.migration.MigrationStrategy;
import dio.board.api.ui.MainMenu;

import java.sql.SQLException;

import static dio.board.api.persistence.config.ConnectionConfig.getConnection;

public class BoardApiApplication {

	public static void main(String[] args) throws SQLException {
		try (var connection = getConnection()) {
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
	}

}
