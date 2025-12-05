CREATE TABLE perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE perfil_funcionalidades (
    perfil_id BIGINT NOT NULL,
    funcionalidade VARCHAR(255) NOT NULL,
    PRIMARY KEY (perfil_id, funcionalidade),
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);
