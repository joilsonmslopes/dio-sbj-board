package dio.board.api.persistence.dao;

import dio.board.api.persistence.entity.Board;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAO {
    private final Connection connection;

    public Board insert(final Board entity) throws SQLException {
        var sql = "INSERT INTO BOARDS (name) VALUES (?);";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    entity.setId(id);
                }
            }
        }

        return entity;
    }

    public void delete(final Long id) throws SQLException {
        var sql = "DELETE FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<Board> findById(final Long id) throws SQLException {
        var sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                var entity = new Board();
                entity.setId(result.getLong("id"));
                entity.setName(result.getString("name"));

                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            return result.next();
        }
    }
}
