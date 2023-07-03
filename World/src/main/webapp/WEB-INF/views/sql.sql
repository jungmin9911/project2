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

update banner set image='mainimgs4.jpg' where subject=4;
update banner set image='mainimgs5.jpg' where subject=5;
update banner set useyn = 'Y' where bseq=17;
