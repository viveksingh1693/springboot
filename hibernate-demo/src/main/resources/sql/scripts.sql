create table student(
student_id int(11) not null,
file_name varchar(45) default null
)

create table image(
student_id int(11) not null,
file_name varchar(45) default null
)


create table emails(
student_id int(11) not null,
email_order int(11) not null, 
email varchar(45) default null
)