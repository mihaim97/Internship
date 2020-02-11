use `internship`;
delimiter //
drop procedure checkuser;
create procedure checkuser(in p_username varchar(20), inout o_password varchar(80))
begin
#declare tmp_username varchar(20);
#declare tmp_password varchar(20);
#set result = 0;
set o_password = '';

select password
into o_password
from 
	users us 
where 
	us.username = p_username;

#if tmp_username = p_username
#then
#	set result = 1;
 #   set p_password = tmp_password;
#end if;

end;