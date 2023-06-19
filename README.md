# FoodStore

Prova técnica para o Grupo Acert.
acompanhar a evolução do projeto através do Trello: https://trello.com/b/xV07L8Zf/foodstore

projeto desenvolvido utilizando Maven, Java 8, SpringBoot 2, banco de dados FIREBIRD, Docker, e OpenAPI 3.0 (swagger)
testes unitários com Mockito e de integração através do Postman (no futuro, serão integrados como Newman em uma pipeline).

Para rodar o projeto, basta rodar através de uma IDE dando run pelo FoodstoreApplication ou baixando a imagem Docker do seguinte repositório do Docker Hub:

https://hub.docker.com/repository/docker/lucasbjacob/food_store

baixando a imagem, deve-se rodar através do comando

docker pull lucasbjacob/food_store

o projeto pode ser testado via Swagger pelo endpoint

http://localhost:8080/swagger-ui/index.html#/

#ENDPOINTS:

User:
POST localhost:8080/foodstore/user - criar usuário
POST localhost:8080/foodstore/user/login/auth - logar usuário existente

Cliente:
POST localhost:8080/foodstore/cliente - criar cliente
GET localhost:8080/foodstore/cliente - buscar clientes
GET localhost:8080/foodstore/cliente/cpf/{cpf} - buscar cliente por cpf
PUT localhost:8080/foodstore/cliente/cpf/{cpf} - atualizar cliente
DELETE localhost:8080/foodstore/cliente/cpf{cpf} - deletar cliente

Pedido:
POST localhost:8080/foodstore/pedido - criar pedido
POST localhost:8080/foodstore/pedido/num/{num} - adicionar produto no pedido
GET localhost:8080/foodstore/pedido - buscar pedidos
GET localhost:8080/foodstore/pedido/num/{num} - buscar pedido por numero
PUT localhost:8080/foodstore/pedido/num/{num} - atualizar pedido
DELETE localhost:8080/foodstore/pedido/num{num} - deletar pedido

Entrega:
POST localhost:8080/foodstore/entrega - criar entregar
POST localhost:8080/foodstore/entrega/id{id} - atualizar status para entregue
GET localhost:8080/foodstore/entrega - buscar entregas
GET localhost:8080/foodstore/entrega/id/{id} - buscar entrega por id
PUT localhost:8080/foodstore/entrega/id/{id} - atualizar entrega
DELETE localhost:8080/foodstore/entrega/id{id} - deletar entrega
