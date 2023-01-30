
INSERT INTO product (id, name)
    VALUES (35455, 'black shirt');

INSERT INTO brand (name)
    VALUES ('Zara');

INSERT INTO price (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
    VALUES (1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 35455, 1, 38.95, 'EUR');




