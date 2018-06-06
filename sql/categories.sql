create table categories(
	id SERIAL primary key auto_increment not null,
	type varchar(100) not null
);

insert into categories values(1, '選択してください');
insert into categories values(2, '食費');
insert into categories values(3, '日用品');
insert into categories values(4, '交際費');