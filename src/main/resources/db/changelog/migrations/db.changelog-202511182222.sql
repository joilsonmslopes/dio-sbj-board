--liquibase formatted sql
--changeset joilson:202511182222
--comment: cards table create

CREATE TABLE CARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    board_column_id BIGINT NOT NULL,
    CONSTRAINT fk_boards_columns__cards_id FOREIGN KEY (board_column_id) REFERENCES BOARDS_COLUMNS(id) ON DELETE CASCADE
) ENGINE=InnoDB;

--rollback DROP TABLE CARDS