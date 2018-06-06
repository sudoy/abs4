create table details(
	id SERIAL primary key auto_increment not null,
	date date not null,
	type_id int not null, 
	content varchar(100),
	cost int  not null
);

insert into details values(1, '2018/05/30', 3, 'ティッシュペーパー、歯磨き粉など', -740);
insert into details values(2, '2018/05/30', 2, 'ランチ', -800);
insert into details values(3, '2018/05/30', 4, null, -6800);
insert into details values(4, '2018/05/31', 5, null, 120000);
insert into details values(5, '2018/05/31', 4, null, -6800);

<!--select date, type, content cost from details d JOIN categories ON d.type_id = categories.id ORDER BY d.id;-->