# ==============================================================================
# API Collection: Módulo Administrativo
# ==============================================================================

# ------------------------------------------------------------------------------
# 1. ESPECIALIDADES
# ------------------------------------------------------------------------------

# Criar uma nova especialidade
curl --location 'http://localhost:8080/administrativo/api/especialidades' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Psicologia",
    "descricao": "Tratamento de transtornos mentais e emocionais."
}'
curl --location 'http://localhost:8080/administrativo/api/especialidades' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Psicologia",
    "descricao": "Tratamento de transtornos mentais e emocionais."
}'

curl --location 'http://localhost:8080/administrativo/api/especialidades'

curl --location 'http://localhost:8080/administrativo/api/especialidades/1'

curl --location --request PUT 'http://localhost:8080/administrativo/api/especialidades/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Cardiologia Clínica",
    "descricao": "Cuida de doenças do coração e do sistema circulatório, com foco em tratamento clínico."
}'

curl --location --request DELETE 'http://localhost:8080/administrativo/api/especialidades/1'

curl --location 'http://localhost:8080/administrativo/api/especialidades/1/medicos'

curl --location 'http://localhost:8080/administrativo/api/medicos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Dr. House",
    "crm": "12345-SP",
    "especialidadeId": 1
}'

curl --location 'http://localhost:8080/administrativo/api/medicos'

curl --location 'http://localhost:8080/administrativo/api/medicos/1'

curl --location --request PUT 'http://localhost:8080/administrativo/api/medicos/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Dr. Gregory House",
    "crm": "54321-RJ",
    "especialidadeId": 2
}'

curl --location --request DELETE 'http://localhost:8080/administrativo/api/medicos/1'

curl --location 'http://localhost:8080/administrativo/api/convenios' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Saúde Total",
    "cnpj": "11.222.333/0001-44",
    "email": "contato@saudetotal.com",
    "telefone": "11987654321"
}'

curl --location 'http://localhost:8080/administrativo/api/convenios'

curl --location 'http://localhost:8080/administrativo/api/convenios/1'

curl --location --request PUT 'http://localhost:8080/administrativo/api/convenios/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Saúde Total Plus",
    "cnpj": "11.222.333/0001-44",
    "email": "contato.plus@saudetotal.com",
    "telefone": "11999999999"
}'

curl --location --request DELETE 'http://localhost:8080/administrativo/api/convenios/1'
# Listar todas as especialidades
curl --location 'http://localhost:8080/administrativo/api/especialidades'

# Buscar uma especialidade por ID
curl --location 'http://localhost:8080/administrativo/api/especialidades/1'

# Atualizar uma especialidade
curl --location --request PUT 'http://localhost:8080/administrativo/api/especialidades/1' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Cardiologia Clínica",
    "descricao": "Cuida de doenças do coração e do sistema circulatório, com foco em tratamento clínico."
}'

# Deletar uma especialidade
curl --location --request DELETE 'http://localhost:8080/administrativo/api/especialidades/1'

# Listar todos os médicos de uma especialidade
curl --location 'http://localhost:8080/administrativo/api/especialidades/1/medicos'

# ------------------------------------------------------------------------------
# 2. MÉDICOS
# ------------------------------------------------------------------------------

# Criar um novo médico
curl --location 'http://localhost:8080/administrativo/api/medicos' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Dr. House",
    "crm": "12345-SP",
    "especialidadeId": 1
}'

# Listar todos os médicos
curl --location 'http://localhost:8080/administrativo/api/medicos'

# Buscar um médico por ID
curl --location 'http://localhost:8080/administrativo/api/medicos/1'

# Atualizar um médico
curl --location --request PUT 'http://localhost:8080/administrativo/api/medicos/1' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Dr. Gregory House",
    "crm": "54321-RJ",
    "especialidadeId": 2
}'

# Deletar um médico
curl --location --request DELETE 'http://localhost:8080/administrativo/api/medicos/1'

# ------------------------------------------------------------------------------
# 3. CONVÊNIOS
# ------------------------------------------------------------------------------

# Criar um novo convênio
curl --location 'http://localhost:8080/administrativo/api/convenios' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Saúde Total",
    "cnpj": "11.222.333/0001-44",
    "email": "contato@saudetotal.com",
    "telefone": "11987654321"
}'

# Listar todos os convênios
curl --location 'http://localhost:8080/administrativo/api/convenios'

# Buscar um convênio por ID
curl --location 'http://localhost:8080/administrativo/api/convenios/1'

# Atualizar um convênio
curl --location --request PUT 'http://localhost:8080/administrativo/api/convenios/1' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Saúde Total Plus",
    "cnpj": "11.222.333/0001-44",
    "email": "contato.plus@saudetotal.com",
    "telefone": "11999999999"
}'

# Deletar um convênio
curl --location --request DELETE 'http://localhost:8080/administrativo/api/convenios/1'
