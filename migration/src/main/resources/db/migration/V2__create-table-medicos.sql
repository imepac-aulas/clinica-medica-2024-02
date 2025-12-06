CREATE TABLE medicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    crm VARCHAR(255) NOT NULL UNIQUE,
    id_especialidade BIGINT NOT NULL,
    FOREIGN KEY (id_especialidade) REFERENCES especialidades(id)
);
