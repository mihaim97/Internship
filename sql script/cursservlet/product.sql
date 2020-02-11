use `internship`;

drop table if exists product;

create table product(
id int primary key auto_increment not null,
pName varchar(20) not null unique,
pType varchar(20) not null,
constraint F_Key foreign key (pType) references producttype(type)
);

insert into product (pName, pType) 
values('Dacia', 'CAR'), ('Renault', 'CAR'), ('BMW', 'CAR'), ('DELL', 'PC'),
('HP', 'PC'), ('LENOVO', 'PC');

select * from internship.product;


