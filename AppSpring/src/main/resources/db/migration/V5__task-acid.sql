-- Чтение неподтверждённых данных
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
BEGIN TRANSACTION
SELECT * FROM products
WHERE CategoryId = 4;
COMMIT;

-- Чтение подтверждённых данных (нет грязного чтения)
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRANSACTION
SELECT * FROM products
WHERE CategoryId = 4;
COMMIT;

-- Повторяемое чтение (нет повторяемого чтения)
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRANSACTION
SELECT * FROM products
WHERE CategoryId = 4;
COMMIT;

-- Сериализуемость (нет фантомных записей)
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
BEGIN TRANSACTION
SELECT * FROM products
WHERE CategoryId = 4;
COMMIT;