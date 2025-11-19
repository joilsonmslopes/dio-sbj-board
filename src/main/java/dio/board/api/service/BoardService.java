package dio.board.api.service;

import dio.board.api.persistence.dao.BoardColumnDAO;
import dio.board.api.persistence.dao.BoardDAO;
import dio.board.api.persistence.entity.Board;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BoardService {

    private final Connection connection;

    public Board insert(final Board entity) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        try {
            dao.insert(entity);
            var columns = entity.getColumns().stream().peek(column -> column.setBoard(entity)).toList();

            for (var column : columns) {
                boardColumnDAO.insert(column);
            }

            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }

        return entity;
    }

    public boolean delete(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        try {
            if (!dao.exists(id)) {
                return false;
            }
            dao.delete(id);
            connection.commit();
            return true;
        } catch (final SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }
}
