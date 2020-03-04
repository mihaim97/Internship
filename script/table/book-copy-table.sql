use `library`;
drop table if exists `copy_stock`;

create table `copy_stock`(
`code` int primary key auto_increment not null,
`flag` varchar(2) not null check (`flag` in ('AV', 'UN')), # AV - available, UN - unavailable
`status` varchar(2) not null check (`status` in ('AV', 'RE', 'PE')), # AV - available, RE - rented, PE - pending
`book_id` int not null,
constraint `FK_Stock_BookId` foreign key (`book_id`) references `books` (`id`) on delete cascade on update cascade
);

#insert into library.`copy_stock` (flag, status, book_id) values ('AV', 'AV', 1);
