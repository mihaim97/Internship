use `library`;

drop table if exists `book_tag_many_to_many`;
drop table if exists `books_authors`;
drop table if exists `authors`;
drop table if exists `book_tags`;
drop table if exists `books`;

create table `books`(
`id` int primary key auto_increment not null,
`title` varchar(50) not null,
`description` varchar(250) not null,
`date_added` datetime not null
);

create table `authors`(
`id` int primary key auto_increment not null,
`name` varchar(50) not null unique
);

create table `books_authors`(
`bookid` int not null,
`authorid` int not null,
primary key (`bookid`, `authorid`),
constraint `FK_BA_BookId` foreign key (`bookid`) references `books` (`id`) on delete cascade on update cascade,
constraint `FK_BA_AuthId` foreign key (`authorid`) references `authors` (`id`) on delete cascade on update cascade
);

create table `book_tags`(
`id` int primary key auto_increment not null,
`tag` varchar(50) not null unique
);

create table `book_tag_many_to_many`(
`bookid` int not null,
`tagid` int not null,
primary key (`bookid`, `tagid`),
constraint `FK_BT_BookId` foreign key (`bookid`) references `books` (`id`) on delete cascade on update cascade,
constraint `FK_BT_AuthId` foreign key (`tagid`) references `book_tags` (`id`) on delete cascade on update cascade
);



