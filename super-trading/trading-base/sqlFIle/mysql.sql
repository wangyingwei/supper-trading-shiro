#创建测试数据库表
CREATE TABLE user_info (  
id int(11) NOT NULL AUTO_INCREMENT,  
user_name varchar(40) NOT NULL,  
password varchar(255) NOT NULL,  
age int(4) NOT NULL,  
PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ;

#插入测试数据
insert into user_info(id,user_name,password,age) values (1,'user1','1',24); 



