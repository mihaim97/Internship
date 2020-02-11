use `internship`;

drop table if exists producttype;

create table producttype(
`type` varchar(20) not null primary key
);

insert into internship.producttype values('CAR'), ('PC')
