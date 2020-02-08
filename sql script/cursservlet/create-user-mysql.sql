CREATE USER 'mihai'@'localhost' IDENTIFIED BY 'mihai';
GRANT ALL PRIVILEGES ON * . * TO 'mihai'@'localhost';

#REVOKE FROM 'jack@localhost';
#DROP USER 'mihai'@'localhost';
#select * from mysql.user;
#delete from mysql.user where user = 'mihai'
#flush privileges;

# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#ALTER USER 'mihai'@'localhost' IDENTIFIED WITH mysql_native_password BY 'mihai';