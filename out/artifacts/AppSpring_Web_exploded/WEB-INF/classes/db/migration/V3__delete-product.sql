BEGIN TRANSACTION
DELETE FROM products
WHERE ProductName='AMD Radeon RX 580 8GB';
IF @@ERROR = 0
    COMMIT;
ELSE
    ROLLBACK;