use `library`;

drop table if exists `bookTagManyToMany`;
drop table if exists `booksAuthors`;
drop table if exists `authors`;
drop table if exists `bookTags`;
drop table if exists `books`;

create table `books`(
`id` int primary key auto_increment not null,
`title` varchar(50) not null,
`description` varchar(250) not null,
`dateAdded` datetime not null
);

create table `authors`(
`id` int primary key auto_increment not null,
`name` varchar(50) not null unique
);

create table `booksAuthors`(
`bookId` int not null,
`authorId` int not null,
primary key (`bookId`, `authorId`),
constraint `FK_BA_BookId` foreign key (`bookId`) references `books` (`id`) on delete cascade on update cascade,
constraint `FK_BA_AuthId` foreign key (`authorId`) references `authors` (`id`) on delete cascade on update cascade
);

create table `bookTags`(
`id` int primary key auto_increment not null,
`tag` varchar(50) not null unique
);

create table `bookTagManyToMany`(
`bookId` int not null,
`tagId` int not null,
primary key (`bookId`, `tagId`),
constraint `FK_BT_BookId` foreign key (`bookId`) references `books` (`id`) on delete cascade on update cascade,
constraint `FK_BT_AuthId` foreign key (`tagId`) references `bookTags` (`id`) on delete cascade on update cascade
);



