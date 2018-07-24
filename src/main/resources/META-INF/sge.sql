create database sge;
use sge;

create table if not exists estado (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        abv VARCHAR(10) 
)ENGINE = innodb;
drop table estado;
insert into estado (nome, abv) values ("SAO PAULO", "SP");
select * from estado;

create table if not exists cidade (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        codigo VARCHAR(50) NOT NULL,
        cep VARCHAR(50) NOT NULL,
        cidade_estado_fk BIGINT,
        constraint cidade_estado_fk foreign key (id) references estado(id)
)ENGINE = innodb;
drop table cidade;
insert into cidade (nome, codigo, cep, estado_fk) values ("uberlandia", "abc", "38400", 1);
//insert into cidade (nome, codigo, cep) values ("uberaba", "abc", "38400");
select * from cidade inner join estado on cidade.cidade_estado_fk=estado.id;

create table if not exists tipoLogradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL
)ENGINE = innodb;
drop table tipoLogradouro;
insert into tipoLogradouro (nome) values ("RUA");
select * from tipoLogradouro;

create table if not exists logradouro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        logradouro_cidade_fk BIGINT,
        logradouro_tipo_fk BIGINT,
        constraint logradouro_cidade_fk foreign key (id) references cidade(id),
        constraint logradouro_tipo_fk foreign key (id) references tipoLogradouro(id)
)ENGINE = innodb;
drop table logradouro;
insert into logradouro (nome, cidade_fk, tipo_fk) values ("Rondon", 1, 1);
select * from logradouro;

select tipoLogradouro.nome as tipoLogradouro, logradouro.nome as logradouro, cidade.nome as cidade, estado.nome as estado from logradouro 
inner join tipoLogradouro on logradouro.logradouro_tipo_fk=tipoLogradouro.id 
inner join cidade on logradouro.logradouro_cidade_fk=cidade.id 
inner join estado on cidade.cidade_estado_fk=estado.id;


create table if not exists bairro (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        bairro_cidade_fk BIGINT,
        constraint bairro_cidade_fk foreign key (id) references cidade(id)
)ENGINE = innodb;

insert into bairro (nome, cidade) values ("brasil", 1);
select bairro.nome as bairro, cidade.nome as cidade from bairro inner join cidade on bairro.bairro_cidade_fk=cidade.id; 


