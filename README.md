# Product Management
This project was created as a challenge of a software company.
It is possible:
##### Product
* Create a new product
* Get a list of all products
* Update a product
##### ORDER
* Placing an order
* Retrieving all orders within a given time period

# Application setup (Locally)

## Requirements
Before running tha application, ensure the following dependencies are installed:

```
Java 11
Maven 3.2.2
Git
```

Você deve prover as seguintes variáveis de ambiente:
 - spring.datasource.platform
 - spring.datasource.url
 - spring.datasource.username
 - spring.datasource.password
 - spring.jpa.properties.hibernate.dialect
 - spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults

## Instalação
Baixe o projeto:
```
git clone https://github.com/crusuer/DesafioRepassaBackend.git
```
Acesse o projeto:
```
cd [diretorio_do_projeto]
```
É necessário compilar o código e baixar as dependencias do projeto:
```
mvn clean package
```
Após isso, inicie a aplicação:
```
mvn spring-boot:run
```
# Endpoints da API
Coleção com todas as chamadas da API no Postman: <p>
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/6d560911fe9badcda14d)
- Se quiser chamar a API localmente, considere `HOST` como *http://localhost:8080*
- Se quiser chamar a API publicada na Nuvem, considere `HOST` como *https://desafio-repassa.herokuapp.com*

## ADMIN
### GET
`HOST`[/admin/employees](#get-admin-employees) <br/>
`HOST`[/admin/employees/{id}](#get-admin-employees-id) <br/>
`HOST`[/admin/evaluations](#get-admin-evaluations) <br/>
`HOST`[/admin/evaluations/{id}](#get-admin-evaluations-id) <br/>

### POST
`HOST`[/admin/employees](#get-admin-employees) <br/>
`HOST`[/admin/evaluations](#get-admin-evaluations) <br/>
`HOST`[/admin/assign](#get-admin-assign) <br/>

### PUT
`HOST`[/admin/employees/{id}](#get-admin-employees-id) <br/>
`HOST`[/admin/evaluations/{id}](#get-admin-evaluations-id) <br/>

### DELETE
`HOST`[/admin/employees/{id}](#get-admin-employees-id) <br/>
`HOST`[/admin/evaluations/{id}](#get-admin-evaluations-id) <br/>
<br/>
## FUNCIONÁRIO
### GET
`HOST`[/user/evaluations](#get-user-evaluations) <br/>
### PUT
`HOST`[/user/evaluations/{id}](#put-user-evaluations-id) <br/>
___
Para ver a documentação da servico no SWAGGER, acesse `HOST`[/swagger-ui.html](#get-swagger-ui)