create database school;

create table user
(
    userId  varchar(255) primary key ,
    userPwd varchar(255),
    name    varchar(255),
    sex     varchar(255),
    birth   varchar(255),
    nation  varchar(255),
    hobby   varchar(255),
    memo    varchar(255)
);

insert into user values('1', 'root', 'zhangsan1', '1', '2003-08-23', '汉族', 'BTS', 'memo1');
insert into user values('2', 'root', 'zhangsan2', '1', '2003-08-23', '汉族', 'BTS', 'memo2');
insert into user values('3', 'root', 'zhangsan3', '0', '2003-08-23', '汉族', 'BTS', 'memo3');
insert into user values('4', 'root', 'zhangsan4', '1', '2003-08-23', '汉族', 'BTS', 'memo4');
insert into user values('5', 'root', 'zhangsan5', '0', '2003-08-23', '汉族', 'BTS', 'memo5');
insert into user values('6', 'root', 'zhangsan6', '1', '2003-08-23', '汉族', 'BTS', 'memo6');
insert into user values('7', 'root', 'zhangsan7', '0', '2003-08-23', '汉族', 'BTS', 'memo7');
insert into user values('8', 'root', 'zhangsan8', '1', '2003-08-23', '汉族', 'BTS', 'memo8');
insert into user values('9', 'root', 'zhangsan9', '1', '2003-08-23', '汉族', 'BTS', 'memo9');
select * from user;
delete from user;
