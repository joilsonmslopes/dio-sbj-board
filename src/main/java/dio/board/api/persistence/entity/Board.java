package dio.board.api.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public class Board {
    private Long id;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BoardColumn> columns = new ArrayList<>();

    public BoardColumn getInitialColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(BoardColumnKindEnum.INITIAL));
    }

    public BoardColumn getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(BoardColumnKindEnum.CANCEL));
    }

    private BoardColumn getFilteredColumn(Predicate<BoardColumn> filter){
        return columns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }
}
