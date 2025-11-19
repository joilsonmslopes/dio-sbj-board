--liquibase formatted sql
--changeset joilson:202511182213
--comment: boards_columns table create

CREATE TABLE BOARDS_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    kind VARCHAR(50) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT fk_boards__boards_columns_id FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,
    CONSTRAINT uk_id_order UNIQUE KEY unique_board_id_order (board_id, `order`)
) ENGINE=InnoDB;

--rollback DROP TABLE BOARDS_COLUMNS