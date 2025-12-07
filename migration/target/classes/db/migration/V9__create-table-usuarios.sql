CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    secretaria_id BIGINT,
    FOREIGN KEY (secretaria_id) REFERENCES secretarias(id)
);
