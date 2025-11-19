package dio.board.api.persistence.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardColumn {
    private Long id;
    private String name;
    private Integer order;
    private BoardColumnKindEnum kind;
    private Board board = new Board();
    private List<Card>  cards = new ArrayList<>();
}
