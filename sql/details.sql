create table details(
	id SERIAL primary key auto_increment not null,
	date date not null,
	categ varchar(100) not null, 
	content varchar(100),
	cost int  not null
);

insert into details values(1, '2018/05/30', '日用品', 'ティッシュペーパー、歯磨き粉など', -740);
insert into details values(2, '2018/05/30', '食費', 'ランチ', -800);

create table categories(
	id SERIAL primary key auto_increment not null,
	type varchar(100) not null
);

insert into categories values(1, '選択してください');
insert into categories values(2, '食費');