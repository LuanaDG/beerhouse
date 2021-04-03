-- EXECUTAR NO MySQL/

-- CRIAR BANCO DE DADOS:

CREATE DATABASE craftbeer;


-- CRIAR TABELA:

CREATE TABLE craftbeer.beer (
   id integer AUTO_INCREMENT NOT NULL,
   name varchar(255) NOT NULL,
   ingredients varchar(255) NOT NULL,
   alcohol_content varchar(100),
   price decimal(16,7) NOT NULL,
   category varchar(100) NOT NULL,
   PRIMARY KEY (id)
   );