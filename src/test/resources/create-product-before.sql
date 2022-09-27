delete from product_type;
delete from product;

insert into `product_type` (name) values ("Все десерты");
insert into `product_type` (name) values ("Бенто торты");
insert into `product_type` (name) values ("Торты");

insert into `product` (image, name, price, product_type_id) values ("cake11.jpg", "Бенто торт с надписью", 1500, 2);
insert into `product` (image, name, price, product_type_id) values ("cake3.jpg", "Бенто торт с надписью", 1500, 2);
insert into `product` (image, name, price, product_type_id) values ("bigcake4.jpg", "Двухъярусный торт с фруктами", 1800, 3);
insert into `product` (image, name, price, product_type_id) values ("bigcake7.jpg", "Шоколадный торт", 1800, 3);