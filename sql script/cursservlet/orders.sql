use `internship`;

drop table if exists orders;
drop table if exists ordersinfo;

create table orders(
`id` int primary key not null auto_increment,
`owner` varchar(20) not null,
`orderDate` date not null,
`expDate` date not null,
constraint F_Key_Orders foreign key (`owner`) references users (`username`)
);

insert into orders(`owner`, `orderDate`, `expDate`) 
values ('mihai', curdate(), curdate());

create table ordersinfo(
`id` int primary key not null auto_increment,
`orderId` int not null,
`product` varchar(20) not null,
constraint F_Key_OrderInfoId foreign key (`orderId`) references orders (`id`),
constraint F_Key_OrderInfoProd foreign key (`product`) references product (`pName`)
);

insert into ordersinfo(`orderId`, `product`) values (1, 'Dacia');

