select * from notice;
select * from cart2;

select * from attraction;

select * from LMEMBER;

select*from cart2;


select*from rest_at_view;''

alter table lmember add provider varchar2(30) default 'normal';

alter table lmember add useyn varchar2(30) default 'N';
alter table lmember add address3 varchar2(30) ;

select*from lmember;
select*from banner;
select*from lqna;
select*from attraction;

delete from banner ;

create table banner(
	bseq number(5),
	subject varchar2(30),
	ORDER_SEQ number(3) default 1,
	useyn char(1) default 'Y',
	indate date default sysdate,
	image varchar2(30),
	PRIMARY KEY (bseq)
);

delete from bannerTest;
create sequence banner_seq start with 1;

alter table lqna add passcheck varchar2(10) default 'N';
alter table lqna add pass varchar2(10);
select * from Lworker;

update lqna set passcheck='Y' where lqseq='35';
update lqna set pass='1234' where lqseq='35';

