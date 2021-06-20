CREATE SEQUENCE seq_pk_clientes START 1;

CREATE TABLE Clientes (
	id int PRIMARY KEY DEFAULT nextval('seq_pk_clientes'),
	nome varchar(100),
	sobre_nome varchar(100)
);

CREATE SEQUENCE seq_pk_produto START 1;
CREATE TABLE Produtos (
	id int PRIMARY KEY DEFAULT nextval('seq_pk_produto'),
	nome varchar(100),
	detalhes varchar(400),
	marca varchar(100),
	valor float
);

CREATE SEQUENCE seq_pk_pedidos START 1;
CREATE TABLE Pedidos (
	id int PRIMARY KEY DEFAULT nextval('seq_pk_pedidos'),
	id_cliente integer,
	data date,
	faturado SMALLINT,
	entregue SMALLINT,
	FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);



CREATE TABLE Produtos_Pedidos (
	id_pedido integer,
	id_produto integer,
	qt integer,
	valor float,
	FOREIGN KEY (id_produto) REFERENCES Produtos(id),
	FOREIGN KEY (id_pedido) REFERENCES Pedidos(id),
	PRIMARY key(id_pedido , id_produto )
);

ALTER TABLE clientes ADD COLUMN data_nasc date;

CREATE SEQUENCE seq_pk_servicos;

CREATE TABLE servicos (
	id int PRIMARY KEY DEFAULT nextval('seq_pk_servicos'),
	name varchar(30),
	valor float
);

CREATE TABLE servicos_clientes(
	id_cliente integer,
	id_servico integer,
	FOREIGN KEY (id_cliente ) REFERENCES clientes(id),
	FOREIGN KEY (id_servico ) REFERENCES servicos(id)
);



INSERT INTO servicos (name, valor) VALUES ('servico 1', 1.00),('servico 2', 2.00),('servico 3', 4.50);

CREATE SEQUENCE seq_pk_usuario;

CREATE TABLE usuarios (
	id int PRIMARY KEY DEFAULT nextval('seq_pk_usuario'),
	nome varchar(50),
	email varchar(50),
	img varchar(100),
	login varchar(40),
	senha varchar(40),
	root boolean
);
INSERT INTO usuarios (nome, email, login, senha, root) VALUES ('admin admin',	'admin@admin.com', 'admin', 'ee10c315eba2c75b403ea99136f5b48d', true);
