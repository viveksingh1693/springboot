create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert ignore into users values ('user','{noop}EazyBytes@12345','1');
insert ignore into authorities values ('user','read');

insert ignore into users values ('admin','{bcrypt}$2a$10$YUxKLYAbWtyzIWI3YACRsedtc/yo10XSIdDl/S8NSsDjWk.j7rnmS','1');
insert ignore into authorities values ('admin','admin');

create table customer(
    id int not null auto_increment,
    email varchar(45) not null,
    pwd varchar(200) not null,
    role varchar(45) not null,
    primary key (id)
)
insert ignore into customer(email,pwd, role) values ('user@example.com','{noop}12345','read');
insert ignore into customer(email,pwd, role) values ('admin@example.com','{bcrypt}$2a$12$sn6EPYsxS2da9EalePlY9OUtL4ZCJc9TVctKWyQE1bfzjvWTCuWbW
','admin');
