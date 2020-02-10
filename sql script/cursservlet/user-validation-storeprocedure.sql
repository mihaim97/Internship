use `internship`;
delimiter //
drop procedure checkuser;
create procedure checkuser(in p_username varchar(20), in p_password varchar(20), inout result int)
begin
declare tmp_username varchar(20);
declare tmp_password varchar(20);
set result = 0;

select username, password
into tmp_username, tmp_password
from 
	users us 
where 
	us.username = p_username and us.password = p_password;

if tmp_username = p_username and tmp_password = p_password
then
	set result = 1;
end if;

end;