create database digital_innovation_one;
use digital_innovation_one;

create table aluno(
	id integer primary key auto_increment,
    nome varchar(80) not null,
    idade integer not null,
    estado character(2) not null
);

select * from aluno;

insert into aluno(nome, idade, estado) values ('Pedro', 29, 'RJ');
insert into aluno(nome, idade, estado) values ('Maria', 35, 'AC');
insert into aluno(nome, idade, estado) values ('Joao', 10, 'SC');
insert into aluno(nome, idade, estado) values ('Ana', 51, 'GO');

drop database digital_innovation_one;