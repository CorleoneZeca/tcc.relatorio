
create table "TCC".TIPO_PRODUTO
(
	ID BIGINT not null primary key,
	DESCRICAO VARCHAR(500)
);

create table "TCC".PRODUTO
(
	ID BIGINT not null primary key,
	DATA_REFERENCIA DATE,
	DESCRICAO VARCHAR(500),
	ID_EMPRESA BIGINT,
	ID_USUARIO BIGINT,
	QUANTIDADE INTEGER,
	VALOR_CUSTO NUMERIC(19,2),
	VALOR_UNITARIO NUMERIC(19,2),
	ID_TIPO_PRODUTO BIGINT not null,
	VER INTEGER
);

