use `internship`;

drop table if exists internship.users;

create table users(
`username` varchar(20) not null,
`password` varchar(80) not null,
constraint PK_user primary key (username)
);

insert into internship.users (username, password) values ('mihai',
 '$2a$10$ZyYIZn6yS8qzNVBJPJGTbOjzR4j/z6j4JY8oinVJjEYROsw.MUwX.'); # mihai parola
insert into internship.users (username, password) values ('ana',
 '$2a$10$V76UeyA9HUjPU0ermY5X6OnguD7t2n7fpenzZmIvwJ7hgi2d56w6.'); # ana parola
insert into internship.users (username, password) values ('user',
 '$2a$10$K3CgrVoOanitFrlt0u96R.st0j.s8G/u/JioiWzkHNLkyaqY7sTxy'); # user parola

# pentru parole https://www.browserling.com/tools/bcrypt
 

