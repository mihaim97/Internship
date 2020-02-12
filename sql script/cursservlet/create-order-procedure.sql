use `internship`;
delimiter //
drop procedure createorder;
create procedure createorder(in p_username varchar(20), inout orderid int)
begin

declare isUserValid int default 0;
set orderid = -1;
select count(*) into isUserValid from `user` us where us.username = p_username;

if isUserValid = 1
then

insert into `order` (`owner`, `orderDate`, `expDate`) 
values (p_username, curdate(), date_add(curdate(), INTERVAL 1 MONTH));

#select id into orderid from (select id, max(orderDate) from `order`) od;
select max(id) into orderid from `order` where `owner` = p_username;

end if;

end;