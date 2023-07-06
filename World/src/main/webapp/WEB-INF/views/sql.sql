select * from notice;
select * from cart2;

select * from attraction;

select * from LMEMBER;

select*from cart2;

select * from cart2  where id='one' and result='1' order by cseq desc

select*from rest_at_view;''

alter table lmember add provider varchar2(30) default 'normal';


ALTER TABLE lmember DROP COLUMN useyn;
alter table lmember add useyn varchar2(30) default 'Y';


alter table lmember add address3 varchar2(30) ;

select*from lmember;
select*from banner;
select*from lqna;
select*from rest_at_view;
select*from best_at_view;

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
-----베스트 놀이기구
create or replace view best_at_view
as
select * from
(select rownum, aseq, atname, image from attraction  where bestat='Y'  ) 
where  rownum <=3;

drop view best_at_view;
select*from attraction;
delete from bannerTest;
create sequence banner_seq start with 1;

alter table lqna add passcheck varchar2(10) default 'N';
alter table lqna add pass varchar2(10);
select * from Lworker;

update lqna set passcheck='Y' where lqseq='35';
update lqna set pass='1234' where lqseq='35';

