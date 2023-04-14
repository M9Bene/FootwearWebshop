INSERT INTO shoe (name, brand, price, img_url, detailed_info)
VALUES ('Test Shoe 1', 'nike',
        119.99, 'url1', 'this is a placeholder for a detailed info about the product');

INSERT INTO shoe (name, brand, price, img_url, detailed_info)
VALUES ( 'Test Shoe 2', 'nike', 109.99, 'url2'
       , 'this is a placeholder for a detailed info about the product');

INSERT INTO shoe (name, brand, price, img_url, detailed_info)
VALUES ( 'Test Shoe 3', 'adidas', 129.99, 'url3'
       , 'this is a placeholder for a detailed info about the product');


INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (40, 5, 1);

INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (41, 5, 1);

INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (40, 5, 2);

INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (41, 5, 2);

INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (42, 1, 3);

INSERT INTO size_and_quantity (size, quantity, shoe_id)
VALUES (43, 1, 3);