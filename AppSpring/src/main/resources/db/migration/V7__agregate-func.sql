-- Кол-во заказов пользователя и сумма его заказов
select users.UserName,
       count(sales.SaleID) as SaleCount,
       sum(Price) as TotalSum,
       avg(Price) as AvgSum
from users
join sales on users.UserId = sales.UserId
join products on sales.ProductId = products.ProductId
where UserName like N'%е%'
group by users.UserName;

-- Сумма все товаров по категориям
select CategoryName,
       sum(Price) as SumPrices
from category
join dbo.products p on category.CategoryId = p.CategoryId
group by CategoryName
having avg(Price) > 300;
