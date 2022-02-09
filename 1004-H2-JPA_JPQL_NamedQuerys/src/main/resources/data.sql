
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
