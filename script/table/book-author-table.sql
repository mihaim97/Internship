use `library`;

drop table if exists `booksAuthors`;
drop table if exists `authors`;
drop table if exists `bookDescriptions`;
drop table if exists `bookTags`;
drop table if exists `books`;

create table `books`(
`id` int primary key auto_increment not null,
`title` varchar(50) not null,
`dateAdded` date not null
);

create table `authors`(
`id` int primary key auto_increment not null,
`name` varchar(50) not null,
`addDate` date not null
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
`tag` varchar(50) not null,
`bookId` int not null,
constraint `FK_BookId_Tags` foreign key (`bookId`) references `books` (`id`) on delete cascade on update cascade
);

create table `bookDescriptions`(
`id` int primary key auto_increment not null,
`desc` varchar(80) not null,
`status` int not null,
`bookId` int not null,
constraint `FK_BookId_Description` foreign key (`bookId`) references `books` (`id`) on delete cascade on update cascade
);



