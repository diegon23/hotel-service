CREATE TABLE IF NOT EXISTS clientes(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30),
	cpf VARCHAR(30),
	email VARCHAR(30),
	dt_nascimento TIMESTAMP,
	dt_cadastro TIMESTAMP
);

CREATE TABLE IF NOT EXISTS quartos(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	numero bigint,
	status varchar(30),
	valor_diaria bigint
);

CREATE TABLE IF NOT EXISTS reservas(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_cliente BIGINT,
	id_quarto BIGINT,
	valor_total decimal,
    dt_inicio TIMESTAMP,
    dt_fim TIMESTAMP,
	status varchar(30),
	foreign key (id_cliente) references clientes(id),
	foreign key (id_quarto) references quartos(id)
);

CREATE TABLE IF NOT EXISTS quartos_limpeza(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_quarto BIGINT,
	ativo boolean,
	foreign key (id_quarto) references quartos(ID)
);