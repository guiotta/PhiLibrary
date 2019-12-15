# PhiMovies
Aplicação desenvolvida para avaliação técnica.

Ferramenta para controlar o funcionamento de uma locadora de filmes, permitindo que múltiplos usuários acessem  o seu catálogo e aluguem e devolvam os filmes préviamente alugados.

## Desenvolvimento
Para o desenvolvimento desta aplicação, foi utilizada a linguagem de programação Java e o banco de dados MySQL 8.0.18.0.

Como ferramentas de desenvolvimento, foram utilizadas a IDE Eclipse, com os plugins do Spring Tool Suite 4, o Maven para controle de dependências e o MySQL Workbench.

### Web-Service REST
O Web-Service foi desenvolvido utilizando os Frameworks da Stack Spring para agilizar e simplicar as rotinas necessárias para imlementar um serviço REST, além de facilitar os testes e a manutenção do código escrito.

A camada de comunicação com o banco de dados foi feita utilizando Spring-Data-JPA em conjunto com o Hibernate, permitindo trabalhar com Entities para a manipulação dos dados.

### Especificação da API
A especificação da API foi feita em um arquivo de texto e pode ser locallizada no caminho:
  - classpath:/resources/documents/API_Specification.txt

### Camada de persistência
Para a camada de persistência, foi utilizado um banco de dados MySQLem conjunto com Spring-Data e Hibernate.

Esta combinação permitiu manipular as informações salvas na base de dados diretamente como objetos Java.

O Flyway foi adicionado ao projeto para controlar a versão do Schema do banco de dados, garantindo que o banco esteja em um estado conhecido ao iniciar o serviço da aplicação.

### Frontend
Para este projeto, não foi realizado nenhum trabalho de Frontend.

## Realizando Deploy
Após baixar o código deste projeto, será preciso executar alguns comandos Maven antes de iniciar a execução do mesmo.

### Ajustando as configurações
Primeiramente, será preciso confirmar se as configurações no arquivo application.yml estão corretas.

É preciso confirmar as confgurações de Datasource e do Flayway, para apontarem para um servidor de banco de dados existente e um Schema previamente criado.

### Comandos no Maven
 - mvn eclipse:eclipse, para baixar as dependências e configurar o projeto para o Eclipse.
 - mvn clean install, para compilar o código do projeto e executar os testes.
 - mvn spring-boot:run, para executar a aplicação.
 
### Considerações importantes
O projeto está utilizando um certificado SSL autoassinado para permitindo o trafego de informações de forma segura. Caso sejam encontrados problemas com este certificado, existem configurações de SSL no arquivo de application.yml que podem ser removidas para desabilitar esta feature.

Os scripts para criação do banco de dados estão na pasta classpath:/resources/db/migaration.

Dentro do arquivo classpath:/resources/documents/Users_in_database.txt, existem algumas credenciais para os usuários criados dentro dos scripts SQL utilizados pelo Flyway.

Dentro da pasta classpath:/resources/documents/, existe um arquivo com alguns comandos de exemplo para o acesso ao serviço REST utilizando o cURL.
