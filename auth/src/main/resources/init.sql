# 权限表
create table if not exists permission(  
    id varchar(40) primary key,  
    name varchar(40) unique,  
    description varchar(255)  
);

#资源表
create table if not exists resource(  
    id varchar(40) primary key,  
    uri varchar(255) unique,  
    description varchar(255),  
    permission_id varchar(40),  
    constraint rPermission_id_FK foreign key(permission_id) references permission(id)  
);  
  
# 角色表 
create table if not exists role(  
    id varchar(40) primary key,  
    name varchar(40) unique,  
    description varchar(255)  
);  
 
# 用户表 
create table if not exists user(  
    id varchar(40) primary key,  
    username varchar(40) not null unique,  
    password varchar(40) not null  
);  

# 中间表 权限-角色
create table if not exists permission_role(  
    permission_id varchar(40) not null,  
    role_id varchar(40) not null,  
    constraint permission_id_FK foreign key(permission_id) references permission(id),  
    constraint role_id_FK foreign key(role_id) references role(id),  
    constraint primary key(permission_id,role_id)  
);  
# 中间表  用户-角色  
create table if not exists user_role(  
    user_id varchar(40) not null,  
    role_id varchar(40) not null,  
    constraint user_id_FK foreign key(user_id) references user(id),  
    constraint uRole_id_FK foreign key(role_id) references role(id),  
    constraint primary key(user_id,role_id)  
);  