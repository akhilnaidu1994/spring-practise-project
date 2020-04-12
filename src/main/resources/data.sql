insert into course(id,name,created_timestamp,last_updated_timestamp) values (1001,'Spring',sysdate,sysdate)
insert into course(id,name,created_timestamp,last_updated_timestamp) values (1002,'Angular',sysdate,sysdate)
insert into course(id,name,created_timestamp,last_updated_timestamp) values (1003,'Microservices',sysdate,sysdate)

insert into passport(id,number) values (3001,'E123456')
insert into passport(id,number) values (3002,'E545454')
insert into passport(id,number) values (3003,'E124343')

insert into student(id,name,passport_id) values (2001,'Akhil',3001)
insert into student(id,name,passport_id) values (2002,'Naidu',3002)
insert into student(id,name,passport_id) values (2003,'Sagar',3003)

insert into review(id,rating,description,course_id) values (4001,'5','Nice Course',1001)
insert into review(id,rating,description,course_id) values (4002,'4','Nice Course with best teacher',1001)
insert into review(id,rating,description,course_id) values (4003,'5','Great Course',1003)

insert into student_course(student_id,course_id) values (2001,1001)
insert into student_course(student_id,course_id) values (2002,1001)
insert into student_course(student_id,course_id) values (2003,1001)
insert into student_course(student_id,course_id) values (2001,1002)
insert into student_course(student_id,course_id) values (2003,1003)
