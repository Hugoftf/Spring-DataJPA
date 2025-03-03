![imagem local](/imagem_readme/titulo.png)

# Spring Data JPA
 

  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Banco de dados](#Banco-de-Dados)
  - [Mapeando com JPA](#Mapeando-Com-JPA)
  - [Classes e Metodos Teste](#Classe-e-Metodos-Teste)
  - [Many To One](#Many-To-One)
  - [Query Methods](#Query-Methods)
  - [Tecnologias Usadas](#Tecnologias-Usadas)


## Sobre

 Um programa para exemplificar e estudar o acesso a dados com Spring JPA utilizando docker para rodar uma imagem de um banco de dados postgresSQL. 


## Inicio

Crei a base do meu projeto no site [spring initializr](https://start.spring.io/), escolhi a versão, linguas, projeto e depenpêndencias: 

![imagem local](/imagem_readme/basedoprograma.png)

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

## Mapeando com JPA

### Entidades

Criando e mapeando as minha esntidadeas, primeiro a classe autor, já que existe uma coluna na entidade livro que depende do autor:

![imagem Local](imagem_readme/classes/classe_autor.png)

Membros da classe Autor: 

![imagem Local](imagem_readme/classes/campos_autor.png)

Classe Livro agora: 

![imagem Local](imagem_readme/classes/classe_livro.png)

Membros da classe livro:

![imagem Local](imagem_readme/classes/campos_livro.png)


Primeiramente precisamos anotar a classe com o @Entity, ela serve para dizer que a classe é uma entidade, e o spring juntamente com jpa, entenderá que a classe representará uma tabela e deverá ser persistida no banco de dados, e seus atributos serão mapeados para colunas da tabela correspondente. A Anotação @Table é usado para representar a qual tabela do banco de dados a classe de entidade pertence, ela não é obrigatória, tem o parametro nome que serve para personalizar o nome da tabela, e schema, para definir o esquema da tabela, Se você não fornecer o esquema, ele usa o esquema padrão(No caso do postgresSQL é public). A anotação @Data faz parte da biblioteca Lombok, ela serve para gerar automaticamente metodos úteis em tempo de execução da classe, como: toString, getteres e setteres, construtores padrão e com argumentos, hashcode, etc. 

Nos membros, primeiramente, precisamos fornecer qual é a variavel id da minha entidade, ela receberá a anotação @ID, ela serve para marcar que esse é a chave primaria da entidade, e mais pra frente será importante. A anotação @Column serve para configurar as propriedades de mapeamento de uma coluna, você personalizar diversos aspectos do mapeamento entre os atributos da entidade e as colunas do banco de dados, como nome da coluna, tipo de dados, se a coluna pode ser nula ou não, entre outros(Como as colunas ja foram criadas com o comando SQL não necessita personalizar).

Existe um campo da classe autor que está marcada com a anotação @Transient, ela serve para marca um atributo que não deve ser mapeado para uma coluna no banco de dados, por agora, isso será importe para realizar teste individuais em metodos mais pra frente. Nesse campo também existe a anotação @OneToMany, é usada para mapear um relacionamento de um para muitos entre duas entidades. Ou seja, ela indica que uma instância de uma entidade pode estar associada a várias instâncias de outra entidade. Assim como na classe livro existe um membro que recebe a anotação @ManyToOne, é usada para mapear um relacionamento de muitos para um entre duas entidades. Ela indica que várias instâncias de uma entidade estão associadas a uma única instância de outra entidade, e também recebe a anotação @JoinColumn é usada para especificar a coluna que será utilizada para realizar o mapeamento entre duas entidades em um relacionamento. Esses aspectos serão detalhados mais a frente no programa

Existem um campo na classe livro que é marcado como Enum, então foi criado um enum:

![imagem Local](imagem_readme/classes/enum_genero_livro.png)

Ele também recebe a anotação @Enumerated, é usada para especificar como um campo do tipo enum (enumerado) deve ser armazenado no banco de dados(ela casa com o comando SQL na criação da tabela livro coluna genero o constraint que só pode ser recebidos tal valores informados), tem parametros como EnumType.ORDINAL e EnumType.STRING, o EnumType.STRING que é usado nesse caso, Armazena o valor do enum como o nome da constante (como uma string). Ou seja, o valor armazenado no banco será o nome do enum, o EnumType.ORDINAL Armazena o valor do enum como o índice ordinal (posição) do valor no enum. Por exemplo, se o valor FANTASIA(No Enum criado) é o primeiro valor do enum, ele será armazenado como 0 no banco de dados.

### Repository

Na camada repositorio, no jpa, normalmente é usado interface como base, ela serve para herda outra interface presente no [starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa), essa interface é um conjunto de outras interfaces e anotações, que herda metodos e funcionalidades (Como exemplo os metodos crud (já com a lógica) para realizar operações no banco de dados), essas interface depende de dois tipos genéricos que serão importate para realizar operações futuras, no caso ela quer saber qual é classe que será a entidade do meu programa e o tipo da minha chave primaria, no caso as classes são Autor e Livro, e o tipo da chave primaria é a classe que carrega anotação @ID, no caso UUID.

stater data jpa:

![imagem Local](imagem_readme/classes/starter_jpa.png)


Autor repository:

![imagem Local](imagem_readme/classes/interface_autorrepository.png)

Livro repository:

![imagem Local](imagem_readme/classes/interface_livrorepository.png)


## Classe e Metodos Teste

No Spring, por padrão, quando o projeto é criado já é inserido a dependencia [starter test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test), básicamente ela serve para realizar teste através de metodos com nossas entidades e repositorios mocados. (Não irei comentar a lógica no tópico, é uma lógica básica de cruds, somente o comportamento e as anotações)

![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/starter_teste.png)

Como boa prática é indicado criar pacote para separar a lógica do teste para cada entidade e repositorio:


![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/criando_pacoteRepositoryTeste_e_classeAutorRepositoryTeste.png)


Agora, iremos criar a classe autor teste, para testar a lógica de metodos que iremos implementar no programa.

#### Autor Repository Teste


![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/classe_autorRepositoryTesteTeste.png)

A classe AutorTeste recebe a anotação obrigatória @SpringBootTest para dizer que é uma classe teste, e também criamos um campo para o AutorRepository, com uma anotação @Autowired para realizar a [injeção de dependencia](https://medium.com/@leonardogiuliani/autowired-e-a-inje%C3%A7%C3%A3o-de-depend%C3%AAncia-do-spring-d8864cc9af50).

Metodos cruds criados e implementando a lógica e boas práticas: 

Salvar: 

![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/metodo_salvarTeste.png)


Atualizar: 

![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/metodo_atualizarAutor.png)

Encontrar todos os autor e contar:

![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/metodo_encontrarTodosAutor_e_contagemAutorTeste.png)

Deletar por id e deletar autor:

![imagem Local](imagem_readme/classe_applicationTest/autorRepositoryTeste/metodo_deleteAutorPorId_e_deleteAutor.png)

Básicamente foi feito uma lógica em cada metodo para validação e boa prática, para realizar as operações encontradas no campo do AutorRepository, que serve como um intermedio, para realizar operações no banco de dados.

#### Livro Repository Teste

Agora vamos a implementação da classe Livro repository teste. A implementação dos metodos não é muito diferente, o que muda é a lógica.

A classe teste:

![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/classe_livroRepositoryTeste.png)

Crud salvar:

![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/crud_salvar.png)

Resultado no banco de dados:

![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/resultado_salvar.png)

Crud autualizar autor:

Monstrando novas pespectivas do que pode ser feito além do crud padrão, como já foi mostrado na classe autor o padrão, agora, podemos implementar lógicas do sistema que podem acontecer:

![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/crud_atualizar_autor.png)

Como está no banco de dados:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/select_livro_e_autor.png)


Depois de testar o metodo:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/select_livro_e_autor_atualizado.png)


## Many To One

"O Many-to-One  é um tipo de relacionamento entre duas entidades onde muitos registros de uma entidade  estão associados a um único registro de outra entidade." No exemplo desse programa, o many to one está associada a coluna id autor da tabela livro, onde tem o relacionamento de muitos livros para um autor, em lógica, o sistema deve precisar de algum metodo para buscar esse padrão de muitos livros para um autor. Porém o importante na implementação é saber que tipo de carregamento que o sistema precisa, pode ser custoso para o desenpenho ou trazer exesso de informação, para isso existe a estratégia de fetch.

"No contexto do Spring Data JPA e do relacionamento entre entidades, a estratégia de fetch define como os dados relacionados (de outras entidades) são carregados quando uma entidade é consultada no banco de dados. Quando você tem um relacionamento ManyToOne ou OneToMany, o fetch controla a forma como as entidades associadas são recuperadas."

Exemplo, implementamos nosso método de buscar livro e autor:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/metodo_buscar_livro_e_autor.png)


Um metodo com retorno no console da nosso back-end, ele pode servi para apenas recuperar informações do banco de dados, como para lógica para outros métodos e muitas outras coisas.

resultado comum dessa operação:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/metodo_buscar_livro_e_autor_resultado.png)


Vamos dizer agora, que eu não quero carrega junto o autor, somente o livro, na lógica teriamos que comentar as linhas do autor:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/parametro_fetch_many_to_one_comentado.png)


Não é uma boa prática. Existe um parametro no relacionamento many to one chamado fetch:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/parametro_fetch_many_to_one.png)


Ele tem dois tipos, o eager e lazy. " Lazy Fetch (Carregamento preguiçoso): Nesse tipo de estratégia, os dados da entidade associada não são carregados até que sejam realmente necessários. Ou seja, o JPA só consulta o banco para trazer as entidades associadas quando você acessar explicitamente os dados dessas entidades. Eager Fetch (Carregamento ansioso): Com essa estratégia, os dados das entidades associadas são carregados imediatamente, junto com a consulta inicial, sem a necessidade de uma consulta adicional ao banco de dados."

Podemos usar o lazy para filtrar e evitar exesso de informações. Nesse caso agora, precisamos usar também a anotação @Transactional (Quando você marca um método ou uma classe com @Transactional, o Spring automaticamente inicia uma transação quando o método é chamado e a finaliza ao término da execução do método. Caso ocorra algum erro ou exceção dentro do método, a transação é revertida (rollback), garantindo que o banco de dados não fique em um estado inconsistente) no metodo buscarLivrosEAutor:


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/parametro_fetch_many_to_one_transaction.png)


Resultado: 


![imagem local](imagem_readme/classe_applicationTest/livroRepositoryTeste/parametro_fetch_many_to_one_transaction_resultado.png)


Como pode vê, o hibernate fez duas buscas no banco de dados, nesse caso as buscar serão feita se o metodo precisar carregar os dados em que o metodo requere.

## Query Methods

Eles são parte do Spring Data JPA, uma extensão do Spring Data que simplifica o uso de JPA (Java Persistence API) e permite a criação de repositórios de acesso a dados. Com JPA Query Methods, você pode criar consultas de maneira declarativa usando convenções de nomenclatura, o que faz com que o Spring Data JPA gere automaticamente as consultas correspondentes.


![imagem local](imagem_readme/query_methods/query_livro.png)


Você pode encontrar a lista de todas as Query methods [aqui](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)


Na prática, quando você vai usar sua classe de repository elas vão aparecer:


![imagem local](imagem_readme/query_methods/query_livro_prática.png)


## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/)
