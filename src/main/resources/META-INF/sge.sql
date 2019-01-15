drop database sge;

drop table Endereco;
drop table Bairro;
drop table Logradouro;
drop table TipoLogradouro;
drop table Cidade;
drop table Estado;
drop table Usuario;
drop table Tokens;

create database sge;
use sge;
ALTER DATABASE `sge` CHARSET = UTF8 COLLATE = utf8_bin;

-- tabelas

create table Usuario (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        usuario VARCHAR(25),
        senha VARCHAR(30),
        email VARCHAR(50)
);


create table Tokens(
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        usuario_id BIGINT,
        usuario VARCHAR(10),
        email VARCHAR(50),
        token VARCHAR (80)

)Engine = innodb;


create table if not exists Estado (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        abv VARCHAR(5) NOT NULL 
)ENGINE = innodb;

create table if not exists Cidade (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        codigo VARCHAR(50) NOT NULL,
        cep VARCHAR(50) NOT NULL,
        cidade_estado_fk BIGINT NOT NULL,
        constraint cidade_estado_fk foreign key (cidade_estado_fk) references estado(id)
)ENGINE = innodb;

create table if not exists TipoLogradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL
)ENGINE = innodb;

create table if not exists Logradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        logradouro_cidade_fk BIGINT,
        logradouro_tipo_fk BIGINT,
        constraint logradouro_cidade_fk foreign key (logradouro_cidade_fk) references cidade(id),
        constraint logradouro_tipo_fk foreign key (logradouro_tipo_fk) references tipoLogradouro(id)
)ENGINE = innodb;


create table if not exists Bairro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        bairro_cidade_fk BIGINT,
        constraint bairro_cidade_fk foreign key (bairro_cidade_fk) references cidade(id)
)ENGINE = innodb;

create table if not exists Endereco (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        numero INT NOT NULL,
        cep VARCHAR(50),
        complemento VARCHAR(50),
        endereco_logradouro_fk BIGINT,
        endereco_bairro_fk BIGINT,
        constraint endereco_logradouro_fk foreign key (endereco_logradouro_fk) references logradouro(id),
        constraint endereco_bairro_fk foreign key (endereco_bairro_fk) references bairro(id)        
)ENGINE = innodb;


ALTER TABLE `Endereco` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `Bairro` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `Logradouro` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `TipoLogradouro` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `Cidade` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `Estado` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE `Usuario` CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;

-- inserindo dados
insert into Estado (nome, abv) values ("SAO PAULO", "SP");
insert into Estado (nome, abv) values ("MINAS GERAIS", "MG");
insert into Cidade (nome, cod, cep, estado_id) values ("Uberlandia", "34", "38.600-000", 1);
insert into Cidade (nome, cod, cep, estado_id) values ("Paracatu", "38", "38.400-000", 2);
insert into TipoLogradouro (nome) values ("RUA");
insert into TipoLogradouro (nome) values ("AVENIDA");
insert into TipoLogradouro (nome) values ("ALAMEDA");
insert into Logradouro (nome, cidade_id, tipologradouro_id) values ("4", 2, 1);
insert into Bairro (nome, cidade_id) values ("brasil", 1);
insert into Endereco (numero, cep, logradouro_id, bairro_id) values (87, "2423141", 1, 1);

-- limpar tabelas
delete from Endereco;
delete from Bairro;
delete from Logradouro;
delete from TipoLogradouro;
delete from Cidade;
delete from Estado;
delete from Usuario;

-- selecionar tudo
select * from Endereco;
select * from Bairro;
select * from Logradouro;
select * from TipoLogradouro;
select * from Cidade;
select * from Escola;
select * from Estado;
select * from Usuario;
select * from Tokens;

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
        
select * from Escola 
        inner join Endereco on Escola.endereco_id=Endereco.id
        inner join Bairro on Endereco.bairro_id=Bairro.id
        inner join Cidade on Bairro.cidade_id=Cidade.id
        where Cidade.estado_id = 1;
;

select * from Escola as e inner join Endereco as end;

insert into sge.TurmaDisciplina values 