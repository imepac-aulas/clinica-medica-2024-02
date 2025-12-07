curl --location 'http://localhost:8080/administrativo/api/convenios' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Saúde Total",
    "cnpj": "11.222.333/0001-44",
    "email": "contato@saudetotal.com",
    "telefone": "11987654321"
}'