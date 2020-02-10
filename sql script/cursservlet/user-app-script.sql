use `internship`;

drop table if exists internship.users;

create table users(
id int not null auto_increment,
username varchar(20) not null,
password varchar(20) not null,
constraint PK_user primary key (id, username)
);

insert into internship.users (username, password) values ('mihai', 'mihai');
insert into internship.users (username, password) values ('ana', 'ana');
insert into internship.users (username, password) values ('user', 'user');

 

