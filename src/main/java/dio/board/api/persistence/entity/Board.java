package dio.board.api.persistence.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private Long id;
    private String name;
    private List<BoardColumn> columns = new ArrayList<>();
}
