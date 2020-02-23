USE `library`;

drop table if exists `authorities`;
drop table if exists `appusers`;
create table `appusers` (
  `username` varchar(50) not null,
  `password` varchar(80) not null,
  `email` varchar(50) not null unique,
  `enabled` tinyint(1) not null,
  `role` varchar(20) not null,
  primary key (`username`)
); #ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
create table `authorities` (
  `username` varchar(50) not null,
  `authority` varchar(50) not null,
  unique key `authorities_unique_1` (`username`,`authority`),
  constraint `authorities_fkey_1` foreign key (`username`) references `myusers` (`username`)
); #ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/
#parola 1234
# ex parola encode $2a$10$K9z2QHLEiySh7S1AKaxRQe9nWtmQVVsvKw59R2acx.hD5ifT7cxji
INSERT INTO library.`appusers` VALUES ('mihai', md5('parola'), 'email', 1, 'admin');
