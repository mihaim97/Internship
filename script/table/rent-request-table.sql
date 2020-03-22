use `library`;

drop table if exists `pending`;
drop table if exists `book_rent`;
drop table if exists `rent_request`;
drop table if exists library.`pending`;
drop table if exists library.`user_banned`;
drop table if exists library.`book_request`;
drop table if exists `book_request`;

create table `book_rent`(
`id` int primary key auto_increment not null,
`date_rent` datetime not null,
`end_rent` datetime not null,
`status` varchar(2) not null check (`status` in ('ON', 'LA', 'RE')), # O - on going, L - late, R - returned
`note` float null,
`book_copy`int not null,
`book` int not null,
`emp_id` int not null,
 constraint `FK_BookRent_StockBook` foreign key (`book_copy`) references `copy_stock` (`code`),
 constraint `FK_BookRent_Book` foreign key (`book`) references `books` (`id`),
 constraint `FK_BookRent_Employee_Id` foreign key (`emp_id`) references `appusers` (`id`)
);

create table `rent_request`(
`id` int primary key auto_increment not null,
`date_request` datetime not null,
`status` varchar(3) not null check (`status` in ('WAC', 'WFC', 'DE', 'GR')), # WA - waiting, WFC - waiting for conf, DE - declined, GR - granted
`book_id`int not null,
`employee_id` int not null,
 constraint `FK_RentRequest_Book_Id` foreign key (`book_id`) references `books` (`id`),
 constraint `FK_RentRequest_Employee_Id` foreign key (`employee_id`) references `appusers` (`id`),
 index(`date_request`)
);

create table library.`pending`(
`id` int primary key auto_increment not null,
`rent_request_id` int not null,
`copy_stock_id` int not null,
`end_date` datetime not null,
constraint `Pending_Rent_FK` foreign key (`rent_request_id`) references `rent_request` (`id`),
constraint `Unique_Pending_Rent` unique(`rent_request_id`)
);

create table library.`user_banned`(
`id` int primary key auto_increment not null,
`user_id` int not null unique,
`date_end` date not null,
constraint `Banned_User_User_FK` foreign key (`user_id`) references `appusers` (`id`)
);

create table library.`book_request`(
`id` int primary key auto_increment not null,
`title` varchar(50) not null,
`author` varchar(50) not null,
`pubCompany` varchar(50) not null,
`link` varchar(200),
`copyNum` int default 1,
`cost` float not null,
`status` varchar(3) not null check (`status` in ('PE', 'AC', 'RJ'))
);


