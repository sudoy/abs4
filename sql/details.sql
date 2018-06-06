create database abs4;

create table details(
	date date not null,
	categ varchar(100) not null,
	content varchar(100),
	cost int  not null
);

insert into details values('2018/05/30', '日用品', 'ティッシュペーパー、歯磨き粉など', -740);
insert into details values('2018/05/30', '食費', 'ランチ', -800);