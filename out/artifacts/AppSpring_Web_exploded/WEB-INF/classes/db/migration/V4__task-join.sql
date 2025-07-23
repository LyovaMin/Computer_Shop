--все товары и их категория
SELECT products.ProductName, category.CategoryName
FROM products
JOIN category on products.CategoryId = category.CategoryId;

--все пользователи, которые не совершали покупки
SELECT users.UserName, sales.SaleID
FROM users
LEFT JOIN sales ON users.UserId = sales.UserId
WHERE SaleID IS NULL;

--все товары с продажами
SELECT products.ProductName, sales.SaleID
FROM products
RIGHT JOIN sales ON products.ProductId = sales.ProductId;
