create table details(
	id SERIAL primary key auto_increment not null,
	date date not null,
	categ varchar(100) not null, 
	content varchar(100),
	cost int  not null
);

insert into details values(1, '2018/05/30', '日用品', 'ティッシュペーパー、歯磨き粉など', -740);
insert into details values(2, '2018/05/30', '食費', 'ランチ', -800);
insert into details values(3, '2018/05/30', '交際費', null, -6800);
insert into details values(4, '2018/05/31', 'アルバイト代', null, 120000);
insert into details values(5, '2018/05/31', '交際費', null, -6800);
