use `internship`;
delimiter //
drop procedure addorderinfo;
create procedure addorderinfo(in orderid varchar(20), in product varchar(20))
begin
insert into `orderinfo` (`orderId`, `product`) values (orderid, product);
end;