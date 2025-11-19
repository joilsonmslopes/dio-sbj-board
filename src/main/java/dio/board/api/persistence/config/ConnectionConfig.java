package dio.board.api.persistence.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionConfig {

    public static Connection getConnection() throws SQLException {
        var url = "jdbc:mysql://localhost/dio-sbj-board";
        var userBoard = "root";
        var passwordBoard = "diosbj";
        var connection = DriverManager.getConnection(url, userBoard, passwordBoard);
        connection.setAutoCommit(false);
        return connection;
    }
}
