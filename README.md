# ConfectionerySpringWebApp

## How to start
* Для работы с этим приложением сконируйте проект. 
* Создайте БД в MySQL c именем confectionerydb.
* Запустите проект.
* Запустите следующие скрипты в MySQL.
* Зарегистрируйтесь, чтобы войти, как пользователь.
* Чтобы войти, как администратор: 89180000000 admin.
* Для запуска тестов: создайте БД в MySQL c именем confectionerydbtest.

### Скрипт 1:
```SQL
insert into `product_type` (name) values ("Все десерты");
insert into `product_type` (name) values ("Бенто торты");
insert into `product_type` (name) values ("Торты");
insert into `product_type` (name) values ("Пироженые");
insert into `product_type` (name) values ("Капкейки");
insert into `product_type` (name) values ("Макаронсы");
insert into `product_type` (name) values ("Наборы");
```

### Скрипт 2:
```SQL
insert into `productEntity` (image, name, price, product_type_id) values ("cake11.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("cake3.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("cake1.png", "Бенто торт с пейзажем", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("cake7.jpg", "Бенто торт с рисунком", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake4.jpg", "Двухъярусный торт с фруктами", 1800, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake7.jpg", "Шоколадный торт", 1800, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("cake6.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake9.jpg", "Торт с объемным декором", 1900, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake2.jpg", "Капкейк с декором радуги", 120, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake8.jpg", "Капкейки 9 шт", 1500, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("cake8.jpg", "Бенто торт с рисунком", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake5.jpg", "Фруктовый торт", 1500, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake3.jpg", "Торт с объемным декором", 1900, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("set2.jpeg", "Набор из торта и капкейков", 2200, 7);
insert into `productEntity` (image, name, price, product_type_id) values ("set1.jpg", "Набор из капкейков и макаронс", 800, 7);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake7.jpg", "Пироженое", 200, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake8.jpg", "Пироженое Медовик", 200, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake4.jpg", "Пироженые 12 шт", 1700, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake1.jpg", "Пироженые 9 шт", 1500, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake1jpeg", "Пироженые 4 шт", 800, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("mac1.jpg", "Макаронсы 12 шт", 800, 6);
insert into `productEntity` (image, name, price, product_type_id) values ("mac2.jpg", "Макаронс", 80, 6);
insert into `productEntity` (image, name, price, product_type_id) values ("cake4.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("cake5.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("cake9.jpg", "Бенто торт с надписью", 1500, 2);
insert into `productEntity` (image, name, price, product_type_id) values ("mac4.jpg", "Макаронсы ", 2500, 6);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake2.jpg", "Капкейки 12 шт", 1200, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake4.jpg", "Капкейки 3 шт", 400, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("cupcake5.jpg", "Капкейк", 120, 5);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake1.jpg", "Торт с фруктами", 1800, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake2.jpg", "Двухъярусный торт с фруктами", 1800, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("bigcake6.jpg", "Двухъярусный торт с фруктами", 1500, 3);
insert into `productEntity` (image, name, price, product_type_id) values ("set3.jpg", "Набор из торта и капкейков 18 шт", 3000, 7);
insert into `productEntity` (image, name, price, product_type_id) values ("set4.jpg", "Макаронсы 16 шт", 1100, 6);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake2.jpg", "Шоколадное пироженое", 150, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake3.jpg", "Пироженое Наполеон", 150, 4);
insert into `productEntity` (image, name, price, product_type_id) values ("minicake5.jpg", "Пироженое", 150, 4);
update productEntity set status = true
```

### Скрипт 3:
```SQL
insert into roles (name) values ('ADMIN');
insert into users (first_name, password, phone) values ('Администратор', '$2a$12$FJBS1Dd6xGuDSQgY4tpNROZQSy4ds8d2MRyCP5CAwa4TLcrzGo712', '89180000000');
insert into user_roles (user_id, roles_id) values (3, 3);
```
