drop database if exists mybatis;
create database mybatis CHARACTER SET UTF8;
use mybatis;

create table dept(
    dept_id int primary key auto_increment,
    dept_name varchar(50),
    dept_address varchar(50)
);

create table emp(
    emp_id varchar(18) primary key,
    emp_name varchar(50),
    emp_sex char(1),
    dept_id int
);


insert into dept(dept_name,dept_address) values('研发部一部','广州');
insert into dept(dept_name,dept_address) values('研发部二部','广州');
insert into dept(dept_name,dept_address) values('研发部三部','深圳');

insert into emp(emp_id,emp_name,emp_sex,dept_id) values('44152199507052110','张大',"男","1");
insert into emp(emp_id,emp_name,emp_sex,dept_id) values('44152199507052111','张一',"女","1");
insert into emp(emp_id,emp_name,emp_sex,dept_id) values('44152199507052112','张二',"男","1");
select * from dept;
select * from emp;

select d.*,e.* from dept d inner join emp e on d.dept_id = e.dept_id
where d.dept_name like '%研%'
