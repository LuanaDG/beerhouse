

## Caracteristicas do projeto

1. Beer House: 


- Trata-se de um sistema para cadastro de cervejas artesanais via API. <br/>


- O projeto foi criado com o JAVA 8, utilizando SpringBoot e Maven para gerenciar as dependências. <br/>


- O sistema é capaz de inserir, excluir, alterar e listar cervejas. <br/>


- A realização dos testes foi feita utilizando JUnit e Mockito. <br/>


- O Banco de Dados utilizado foi o MySQL e a configuração para realizar a conexão está no arquivo <br/>
	`application.properties` onde devem ser alteradas as configurações do db (url, username and password). <br>
	


- Os scripts para a criação do Banco de Dados e a tabela estão em: <br/>
	`src/main/resources/craftBeer.sql` <br/>
	
	
	
- Para buildar o projeto executar: `mvn clean package`



- Para rodar o projeto: `mvn spring-boot:run`

	
	
- Acesso aos endpoints - `http://localhost:9000/swagger-ui.html`





