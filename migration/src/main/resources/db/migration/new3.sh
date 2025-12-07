curl --location 'http://localhost:8080/administrativo/api/especialidades' \
--header 'Content-Type: application/json' \
--data-raw '{
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