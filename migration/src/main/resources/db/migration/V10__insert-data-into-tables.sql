
-- Inserir dados na tabela de especialidades
INSERT INTO especialidades (nome, descricao) VALUES
('Cardiologia', 'Especialidade médica que se ocupa do diagnóstico e tratamento das doenças que afetam o coração, bem como os outros componentes do sistema circulatório.'),
('Dermatologia', 'Especialidade médica que se ocupa do diagnóstico e tratamento clínico-cirúrgico das doenças que acometem o maior órgão do corpo humano – a pele.'),
('Ortopedia', 'Especialidade médica que cuida da saúde relacionadas aos elementos do aparelho locomotor, como ossos, músculos, ligamentos e articulações.');

-- Inserir dados na tabela de medicos
INSERT INTO medicos (nome, telefone, email, data_nascimento, crm, id_especialidade) VALUES
('Dr. João Silva', '(11) 98765-4321', 'joao.silva@example.com', '1975-03-15', '123456', 1),
('Dra. Maria Santos', '(21) 91234-5678', 'maria.santos@example.com', '1980-07-22', '654321', 2),
('Dr. Pedro Costa', '(31) 95555-1234', 'pedro.costa@example.com', '1982-11-30', '112233', 3);

-- Inserir dados na tabela de pacientes
INSERT INTO pacientes (nome, telefone, email, data_nascimento, numero_cartao_sus, cpf) VALUES
('Carlos Pereira', '(11) 98888-7777', 'carlos.pereira@example.com', '1980-05-10', '123456789012345', '111.222.333-44'),
('Ana Lima', '(21) 97777-8888', 'ana.lima@example.com', '1992-09-20', '987654321098765', '222.333.444-55');

-- Inserir dados na tabela de consultas
INSERT INTO consultas (paciente_id, medico_id, data_hora, status) VALUES
(1, 1, '2024-12-20 10:00:00', 'AGENDADA'),
(2, 2, '2024-12-21 14:30:00', 'AGENDADA');
