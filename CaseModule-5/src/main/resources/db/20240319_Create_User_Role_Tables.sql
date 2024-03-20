use drugstore;
create table if not exists `role` (
    id int not null auto_increment primary key,
    name varchar(50) not null,
    description varchar(50) not null
    );

insert into `role`(name,description) values ('ROLE_ADMIN','Admin'),('ROLE_USER','User');

create table if not exists `user`(
    id int not null auto_increment primary key,
    role_id int not null,
    username varchar(100) not null,
    password varchar(100) not null,
    full_name varchar(100) not null,
    email varchar(100) not null,
    address varchar(200) not null,
    phone varchar(12) not null,
    avatar varchar(100) not null,
    is_activated bool not null,
    constraint users_uk unique(username, email, phone),
    constraint users_roles_fk foreign key (role_id) references `role` (id)
    );

insert into `user` (role_id, username, password, full_name, email, address, phone, avatar, is_activated) values
(1, 'tuadmin', '$2a$12$dHFuwy4CVVjgl8dqt8txWOxiXoj01ELxJd6ln1MMVH38RBvFeymNe', 'Nguyễn Hoàng Tú', 'tunguyen.admin@gmail.com',
 'Hồ Chí Minh', '0908710899', 'avatar1.png', true) -- password: admin
,(2, 'tyuser', '$2a$12$dZgVGo/YkNdoOdcSE3mSbOfrLidft/y/KFFzGRhX.bh//lks07Kuu', 'Nguyễn Văn Tý', 'tynguyen.user@gmail.com',
'Hồ Chí Minh', '0985678910', 'avatar1.jpg', true) -- password: 123456
,(2, 'teouser', '$2a$12$dZgVGo/YkNdoOdcSE3mSbOfrLidft/y/KFFzGRhX.bh//lks07Kuu', 'Nguyễn Văn Tèo', 'teonguyen.user@gmail.com',
'Hồ Chí Minh', '0981234567', 'avatar1.jpg', true) -- password: 123456
;