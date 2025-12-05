CREATE TABLE consultas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    convenio_id BIGINT,
    carteira_convenio VARCHAR(30),
    data_hora DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    prontuario_id BIGINT,
    consulta_original_id BIGINT,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (convenio_id) REFERENCES convenios(id),
    FOREIGN KEY (prontuario_id) REFERENCES prontuarios(id),
    FOREIGN KEY (consulta_original_id) REFERENCES consultas(id)
);
