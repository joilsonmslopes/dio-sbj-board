package dio.board.api.persistence.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Block {
    private Long id;
    private OffsetDateTime blockedAt;
    private String blockedReason;
    private OffsetDateTime unblockedAt;
    private String unblockedReason;
}
