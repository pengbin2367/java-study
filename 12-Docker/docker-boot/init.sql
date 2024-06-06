create table t_user (
                        id int(10) unsigned not null auto_increment primary key,
                        username varchar(50) not null default '' comment '用户名',
                        password varchar(50) not null default '' comment '密码',
                        sex tinyint(4) not null default '0' comment '性别 0=女 1=男',
                        deleted tinyint(4) unsigned not null default '0' comment '删除标志 0=不删除 1=删除',
                        update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
                        create_time timestamp not null default current_timestamp comment '创建时间'
) engine = innodb auto_increment = 1114 default charset = utf8mb4 comment = '用户表';