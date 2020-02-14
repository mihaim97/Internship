use `internship`;
delimiter //
drop procedure retrieveorderinfo;
create procedure retrieveorderinfo(in id int)
begin
select * from orderinfo where orderId = id;
end;