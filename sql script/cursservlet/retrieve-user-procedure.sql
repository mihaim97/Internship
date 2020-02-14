use `internship`;
delimiter //
drop procedure retrieveuser;
create procedure retrieveuser(in p_username varchar(20))
begin
select * from `user` where username = p_username;
end;