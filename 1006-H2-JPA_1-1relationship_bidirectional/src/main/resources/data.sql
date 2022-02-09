
-- Spring Boot already create table for us
--create table person
--(
--   id integer not null,
--   name varchar(255) not null,
--   location varchar(255),
--   birth_date timestamp,
--   primary key(id)
--);

INSERT INTO course_details (ID, fullname, created_date, last_updated_date ) VALUES(10001,  'JAVA', sysdate(),sysdate());
INSERT INTO course_details (ID, fullname, created_date, last_updated_date ) VALUES(10002,  'Spring Core',sysdate(),sysdate());
INSERT INTO course_details (ID, fullname, created_date, last_updated_date ) VALUES(10003,  'Spring Mvc', sysdate(),sysdate());


insert into passport(id,number)
values(40001,'E123456');
insert into passport(id,number)
values(40002,'N123457');
insert into passport(id,number)
values(40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'VBM',40001);
insert into student(id,name,passport_id)
values(20002,'NIl',40002);
insert into student(id,name,passport_id)
values(20003,'Ram',40003);

insert into review(id,rating,description)
values(50001,'5', 'Great Course');
insert into review(id,rating,description)
values(50002,'4', 'Wonderful Course');
insert into review(id,rating,description)
values(50003,'5', 'Awesome Course');