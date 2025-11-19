--liquibase formatted sql
--changeset joilson:202511182213
--comment: board_columns table create

CREATE TABLE BOARD_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    kind VARCHAR(50) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT fk_boards__board_columns_id FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,
    CONSTRAINT uk_id_order UNIQUE KEY unique_board_id_order (board_id, `order`)
) ENGINE=InnoDB;

--rollback DROP TABLE BOARD_COLUMNS