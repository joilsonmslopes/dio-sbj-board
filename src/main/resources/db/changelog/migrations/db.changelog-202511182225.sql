--liquibase formatted sql
--changeset joilson:202511182225
--comment: blocks table create

CREATE TABLE BLOCKS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    blocked_reason VARCHAR(255) NOT NULL,
    unblocked_at TIMESTAMP NULL,
    unblocked_reason VARCHAR(255) NOT NULL,
    card_id BIGINT NOT NULL,
    CONSTRAINT fk_cards__blocks_id FOREIGN KEY (card_id) REFERENCES CARDS(id) ON DELETE CASCADE
) ENGINE=InnoDB;

--rollback DROP TABLE BLOCKS