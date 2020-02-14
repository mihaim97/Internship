use `internship`;
delimiter //
drop procedure userorders;
create procedure userorders(in p_username varchar(20))
begin
/*select 
	* 
from 
	`order` ord inner join `orderinfo` ordinf on ord.id = ordinf.orderId
where ord.owner = p_username;
*/
select *
from `order` ord
where ord.owner = p_username;
end;