use `internship`;

drop table if exists product;

create table product(
id int primary key auto_increment not null,
pName varchar(20) not null
);

insert into product (pName) values('Dacia');

select * from internship.product;


