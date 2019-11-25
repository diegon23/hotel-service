CREATE TABLE IF NOT EXISTS clientes(
	nome VARCHAR(100),
	cpf VARCHAR(30) PRIMARY KEY,
	email VARCHAR(100),
	dt_nascimento TIMESTAMP,
	dt_cadastro TIMESTAMP,
	ativo boolean
);

CREATE TABLE IF NOT EXISTS quartos(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	numero bigint,
	modelo varchar(30),
	valor_diaria bigint
);

CREATE TABLE IF NOT EXISTS reservas(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_cliente varchar(30),
	id_quarto BIGINT,
	valor_total decimal,
    dt_inicio TIMESTAMP,
    dt_fim TIMESTAMP,
	status varchar(40),
	transacao varchar(40),
	pagamento_realizado boolean,
	pagamento_estornado boolean,
	foreign key (id_cliente) references clientes(cpf),
	foreign key (id_quarto) references quartos(id)
);

CREATE TABLE IF NOT EXISTS quartos_limpeza(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_quarto BIGINT,
	ativo boolean,
	foreign key (id_quarto) references quartos(ID)
);