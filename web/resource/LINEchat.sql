drop table if exists rooms;

create table rooms(
    room_id number(10) primary key,
    name varchar(30) not null,
    created_at timestamp default systimestamp not null,
    updated_at timestamp default systimestamp not null
);

create sequence rooms_seq start with 1 increment by 1 maxvalue 10 nocycle nocache;

drop table if exists chats;

create table members(
    member_id varchar2(30) primary key,
    name varchar2(20) not null,
    password varchar2(100) not null,
    created_at timestamp default systimestamp not null,
    updated_at timestamp default systimestamp not null
);

drop table if exists chats;

create table chats(
    chat_id number(20) primary key,
    room_id number(10) constraint chats_roomid_fk references rooms(room_id) ON DELETE CASCADE,
    member_id varchar2(30) constraint chats_memberid references members(member_id) ON DELETE CASCADE,
    content varchar(150) not null,
    created_at timestamp default systimestamp not null,
    updated_at timestamp default systimestamp not null
);

create sequence chats_seq start with 1 increment by 1 maxvalue 9999 nocycle nocache;

select * from all_constraints where table_name = 'chats';

-------------
insert into members(member_id, name, password) values('admin', '관리자', '1234');

insert into rooms(room_id, name) values(rooms_seq.nextval, '드루와');
insert into rooms(room_id, name) values(rooms_seq.nextval, '나랑 대화할 사람');
insert into rooms(room_id, name) values(rooms_seq.nextval, '개발자 모여라');

insert into chats(chat_id, room_id, member_id, content) values(chats_seq.nextval, 1, 'ksy', '나야, 들기름');

commit;
