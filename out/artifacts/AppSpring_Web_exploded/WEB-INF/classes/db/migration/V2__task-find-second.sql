--найти второй по цене продукт
SELECT MIN(Price) FROM  (SELECT DISTINCT TOP 2 Price
                         FROM products
                         ORDER BY Price DESC) as SecondPrice;

SELECT Price FROM products
ORDER BY Price DESC
OFFSET 1 ROW FETCH NEXT 1 ROW ONLY;


