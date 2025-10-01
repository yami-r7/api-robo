CREATE TABLE acao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    idrobo BIGINT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_hora_inicio DATETIME NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_acao_idrobo FOREIGN KEY(idrobo) REFERENCES robo(id)
);