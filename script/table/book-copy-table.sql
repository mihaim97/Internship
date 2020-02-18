use `library`;

drop table if exists `stock`;

create table `stock`(
`code` int primary key auto_increment not null,
`flag` char not null check (`flag` in ('A', 'U')), # A - available, U - unavailable
`status` char not null check (`status` in ('A', 'R', 'P')), # A - available, R - rented, P - pending
`bookId` int not null,
constraint `FK_Stock_BookId` foreign key (`bookId`) references `books` (`id`)
);