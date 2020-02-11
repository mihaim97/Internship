use `internship`;

delimiter //
drop procedure if exists retrieveProducts;
create procedure retrieveProducts()
begin
select * from product;
end;