package dio.board.api.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class Card {

    private Long id;
    private String title;
    private String description;
}
