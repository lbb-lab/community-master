create table user_info
(
    id int auto_increment primary key not null,
    user_name varchar(50),
    password varchar(50),
    phone varchar(50),
    email varchar(50),
    create_time bigint,
    update_time bigint
);