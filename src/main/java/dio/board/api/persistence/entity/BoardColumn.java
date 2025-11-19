package dio.board.api.persistence.entity;

import lombok.Data;

@Data
public class BoardColumn {
    private Long id;
    private String name;
    private Integer order;
    private BoardColumnKindEnum kind;
    private Board board;
}
