CREATE TABLE obstaculo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    idrobo BIGINT NOT NULL,
    data_hora_registro DATETIME NOT NULL,
    distancia DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_obstaculo_idrobo FOREIGN KEY(idrobo) REFERENCES robo(id)
);