use `library`;

drop table if exists `bookRent`;
drop table if exists `rentRequest`;
drop table if exists `bookRequest`;

create table `bookRent`(
`id` int primary key auto_increment not null,
`dateRent` date not null,
`endRent` date not null,
`status` char not null check (`status` in ('O', 'L', 'R')), # O - on going, L - late, R - returned
`note` float null,
`bookCopy`int not null,
`employeeName` varchar(50) not null,
 constraint `FK_BookRent_StockBook` foreign key (`bookCopy`) references `stock` (`code`),
 constraint `FK_BookRent_Employee_Id` foreign key (`employeeName`) references `users` (`username`)
);

create table `rentRequest`(
`id` int primary key auto_increment not null,
`dateRequest` date not null,
`status` varchar(3) not null check (`status` in ('W', 'WFC', 'D', 'G')), # W - waiting, WFC - waiting for conf, D - declined, G - granted
`bookId`int not null,
`employeeName` varchar(50) not null,
 constraint `FK_RentRequest_Book_Id` foreign key (`bookId`) references `books` (`id`),
 constraint `FK_RentRequest_Employee_Id` foreign key (`employeeName`) references `users` (`username`)
);

create table `bookRequest`(
`id` int primary key auto_increment not null,
`title` varchar(50) not null,
`author` varchar(50) not null,
`pubCompany` varchar(50) not null,
`link` varchar(200),
`copyNum` int default 1,
`cost` float not null,
`status` varchar(3) not null check (`status` in ('P', 'A', 'R'))
);


