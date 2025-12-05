CREATE TABLE prontuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    queixa_principal VARCHAR(255) NOT NULL,
    diagnostico VARCHAR(255) NOT NULL,
    historico_medico TEXT,
    alergias VARCHAR(255),
    medicamentos_em_uso VARCHAR(255),
    exames_solicitados TEXT,
    resultados_exames TEXT,
    data_abertura DATETIME NOT NULL,
    data_ultima_atualizacao DATETIME,
    observacoes TEXT
);
