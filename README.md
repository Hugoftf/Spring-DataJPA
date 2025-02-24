![imagem local](/imagem_readme/titulo.png)

# Spring Data JPA
 

  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Banco de dados](#Banco-de-Dados)
  - [Tecnologias Usadas](#Tecnologias-Usadas)


## Sobre

 Um programa para exemplificar e estudar o acesso a dados com Spring JPA utilizando docker para rodar uma imagem de um banco de dados postgresSQL.

## Inicio

Antes de começar a se aprofundar em JPA e configurar o banco de dados, como a ideia desse projeto é usar o docker, precisamos criar e subir imagens. Antes de criar as imagemns de aplicações do projeto, preciso criar um network para que os contenier se conectem entre sim. 

No cmd realizei esse comando: 

![imagem Local](imagem_readme/cmd_criando_network.png)

Depois criei uma imagem do postgresSQL apontando para o meu network criado anteriormente:

![imagem Local](imagem_readme/cmd_criando_postgres.png)

e por fim, a imagem do pgadmin4 também apontando para o meu network: 

![imagem Local](imagem_readme/cmd_criando_pgadmin4.png)


Configurando o pgadmin4: 

![imagem Local](imagem_readme/pgadmin_login.png)


Associando ao postgresSQL: 


![imagem Local](imagem_readme/conectando_ao_DB.png)

## Banco de Dados

## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/)
