use `internship`;

drop table if exists `orderinfo`;
drop table if exists `order`;

create table `order`(
`id` int primary key not null auto_increment,
`owner` varchar(20) not null,
`orderDate` date not null,
`expDate` date not null,
constraint F_Key_Orders foreign key (`owner`) references `user` (`username`)
);

insert into `order`(`owner`, `orderDate`, `expDate`) 
values ('mihai', curdate(), curdate());

create table `orderinfo`(
`id` int primary key not null auto_increment,
`orderId` int not null,
`product` varchar(20) not null,
constraint F_Key_OrderInfoId foreign key (`orderId`) references `order` (`id`) on delete cascade,
constraint F_Key_OrderInfoProd foreign key (`product`) references product (`pName`)
);

insert into orderinfo(`orderId`, `product`) values (1, 'Dacia');

