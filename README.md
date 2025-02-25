![imagem local](/imagem_readme/titulo.png)

# Spring Data JPA
 

  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Banco de dados](#Banco-de-Dados)
  - [Tecnologias Usadas](#Tecnologias-Usadas)


## Sobre

 Um programa para exemplificar e estudar o acesso a dados com Spring JPA utilizando docker para rodar uma imagem de um banco de dados postgresSQL.


## Inicio

Crei a base do meu projeto no site [spring initializr](https://start.spring.io/), escolhi a versão, linguas, projeto e depenpêndencias: 

 [img]

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

Com o banco de dados criado, precisamos gerar as tabelas, no projeto vamos simular a publicação de um livro, tendo um autor por trás.

Criação da tabela autor: 

![imagem Local](imagem_readme/postgres/comandosSQL_tabelaautor.png)

criação da tabela livro: 

![imagem Local](imagem_readme/postgres/comandosSQL_tabelalivro.png)

No próximo passo agora no spring, precisamos configurar o driver do banco de dados para o spring iniciar.

Substitui o arquivo propeties por yamal, e ficou assim:

![imagem Local](imagem_readme/postgres/configuracao_spring_db_yamal.png)

Com isso, o gerenciamento do spring é capaz de rodar o programa com o driver padrão, porém a ideia é se aprofundar em spring, então vamos criar e configurar o drive na mão:

Criando uma classe com a anotação @Configuration para configurar nossos beans:


![imagem Local](imagem_readme/postgres/classe_databaconfig_campos_variaveis.png)

A classe com a anotação @Configuration, para ser gerenciada pelo spring, os campos com a anotação @value representa variveias que servem para armazenar dados da configuração do datasource.


Segue o bean da classe, com a configuração do driver do banco de dados:

![imagem Local](imagem_readme/postgres/configurando_bean.png)

Uma configuração padrão, a classe Hiraki já é utilizada pelo próprio spring como o padrão.

## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/)
