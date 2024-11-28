<h2>Microserviço cdb</h2>

<p align="center">
 <a href="#Objetivo">Objetivo</a> •
 <a href="#Tecnologias">Tecnologias</a> •
 <a href="#Como rodar">Como rodar</a> •
 <a href="#Rotas">Rotas</a> 
</p>

## Objetivo
Esse microserviço tem como objetivo principal gerir os papeis e as determinadas carteiras de cada customerXpapel

## Tecnologias
Para o desenvolvimento desse projeto, foi utilizado as seguintes tecnologias:
 - Java 17, com springboot framework
 - Banco de dados MySQL

## Como rodar
Para rodar esse serviço, devemos primeiramente ter um serviço kafka rodando, para isso, podemos executar o seguinte docker-compose
```bash
services:
  kafka:
    container_name: kafka
    image: apache/kafka:3.9.0
    ports:
      - 9092:9092
```
Tendo o serviço docker funcional, podemos executar o docker-compose do projeto para subirmos o banco mySQL
```
docker-compose up -d
```
Após, basta executarmos as migrations para configurar o banco, e rodarmos a class Main com o profile development

## Rotas
O projeto dispoe das seguintes rotas, que podem ser testadas pelo ([swagger](http://localhost:8082/api/swagger-ui/index.html))

### GET /wallets
- Essa rota serve para listarmos todas as carteiras cadastradas

### GET /wallets/{customerId}
- Rota utilizada para buscar a carteira de determinado customer

### POST /wallets/{{customer_id}}/buy/{{paper_id}}
- Rota utilizada para a compra de determinado papel, para um usuario.
- Essa rota *NÃO* faz a validação do saldo. A validação é feita no serviço de customer_wallet
- Posta uma mensagem de atualização de saldo no topico XXX

### POST /wallets/{{customer_id}}/sell/{{paper_id}}
- Rota utilizada para a venda de papel para um usuário
- É feita a validação de quantidade antes de efetivar a venda
- É enviada uma mensagem de atualização de saldo no tópico XXX

### POST /papers
- rota utilizada para cadatrar um novo papel
  
### GET /papers
- Rota utilizada para listagem de papeis
