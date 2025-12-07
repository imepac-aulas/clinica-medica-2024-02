-- Usamos INSERT IGNORE para que, se a migração rodar novamente, não ocorra um erro
-- de chave duplicada. Ele simplesmente ignora a inserção de linhas que já existem.

INSERT IGNORE INTO especialidades (id, nome, descricao) VALUES
(1, 'Cardiologia', 'Cuida de doenças do coração e do sistema circulatório.'),
(2, 'Dermatologia', 'Trata de doenças da pele, cabelos e unhas.')
