drop database sge;

drop table endereco;
drop table bairro;
drop table logradouro;
drop table tipoLogradouro;
drop table cidade;
drop table estado;
drop table pessoa;

create database sge;
use sge;

-- tabelas
create table if not exists estado (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        abv VARCHAR(10) 
)ENGINE = innodb;

create table if not exists cidade (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        codigo VARCHAR(50) NOT NULL,
        cep VARCHAR(50) NOT NULL,
        cidade_estado_fk BIGINT,
        constraint cidade_estado_fk foreign key (id) references estado(id)
)ENGINE = innodb;

create table if not exists tipoLogradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL
)ENGINE = innodb;

create table if not exists logradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        logradouro_cidade_fk BIGINT,
        logradouro_tipo_fk BIGINT,
        constraint logradouro_cidade_fk foreign key (id) references cidade(id),
        constraint logradouro_tipo_fk foreign key (id) references tipoLogradouro(id)
)ENGINE = innodb;


create table if not exists bairro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        bairro_cidade_fk BIGINT,
        constraint bairro_cidade_fk foreign key (id) references cidade(id)
)ENGINE = innodb;

create table if not exists endereco (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        numero INT NOT NULL,
        cep VARCHAR(50),
        endereco_logradouro_fk BIGINT,
        endereco_bairro_fk BIGINT,
        constraint endereco_logradouro_fk foreign key (id) references logradouro(id),
        constraint endereco_bairro_fk foreign key (id) references bairro(id)        
)ENGINE = innodb;

-- inserindo dados
insert into estado (nome, abv) values ("SAO PAULO", "SP");
insert into estado (nome, abv) values ("MINAS GERAIS", "MG");
insert into cidade (nome, codigo, cep, cidade_estado_fk) values ("uberlandia", "udia", "38400", 2);
insert into cidade (nome, codigo, cep, cidade_estado_fk) values ("Osasco", "ossc", "38400", 1);
insert into tipoLogradouro (nome) values ("RUA");
insert into tipoLogradouro (nome) values ("AVENIDA");
insert into tipoLogradouro (nome) values ("ALAMEDA");
insert into logradouro (nome, logradouro_cidade_fk, logradouro_tipo_fk) values ("Rondon", 1, 2);
insert into bairro (nome, bairro_cidade_fk) values ("brasil", 1);
insert into endereco (numero, cep, endereco_logradouro_fk, endereco_bairro_fk) values (87, "2423141", 1, 1);

-- limpar tabelas
delete from endereco;
delete from bairro;
delete from logradouro;
delete from tipoLogradouro;
delete from cidade;
delete from estado;

-- selecionar tudo
select * from endereco;
select * from bairro;
select * from logradouro;
select * from tipoLogradouro;
select * from cidade;
select * from estado;

-- selecionar com innerJoin
select cidade.id, cidade.nome as cidade, estado.nome as estado from cidade inner join estado on cidade.cidade_estado_fk=estado.id;
select tipoLogradouro.nome as tipoLogradouro, logradouro.nome as logradouro, cidade.nome as cidade, estado.nome as estado from logradouro 
        inner join tipoLogradouro on logradouro.logradouro_tipo_fk=tipoLogradouro.id 
        inner join cidade on logradouro.logradouro_cidade_fk=cidade.id 
        inner join estado on cidade.cidade_estado_fk=estado.id;
select bairro.nome as bairro, cidade.nome as cidade from bairro inner join cidade on bairro.bairro_cidade_fk=cidade.id;
select tipoLogradouro.nome as tipo, logradouro.nome, endereco.numero, endereco.cep, bairro.nome as bairro, cidade.nome as cidade, estado.nome as estado from endereco
        inner join logradouro on logradouro.id=endereco.endereco_logradouro_fk
        inner join tipoLogradouro on logradouro.logradouro_tipo_fk=tipoLogradouro.id 
        inner join bairro on bairro.id=endereco.endereco_bairro_fk
        inner join cidade on logradouro.logradouro_cidade_fk=cidade.id 
        inner join estado on cidade.cidade_estado_fk=estado.id;
