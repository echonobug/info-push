create table user
(
    id       varchar(64)  not null comment '32位UUID',
    name     varchar(255) not null comment '用户名',
    password varchar(255) not null comment '密码'
)
    comment '用户表';

create unique index user_id_uindex
    on user (id);

create unique index user_name_uindex
    on user (name);

alter table user
    add constraint user_pk
        primary key (id);



