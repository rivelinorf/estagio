create database sge;

use sge;

create table estado (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        abv VARCHAR(10) 
);

insert into estado (nome, abv) values ("MINAS GERAIS", "MG");
select * from estado;