CREATE TABLE algalog.cliente (
	id BIGINT auto_increment NOT NULL,
	nome varchar(60) NOT NULL,
	email VARCHAR(255) NOT NULL,
	telefone varchar(20) NOT NULL,

	primary key (id)
)