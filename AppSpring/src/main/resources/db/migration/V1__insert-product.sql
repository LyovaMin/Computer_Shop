BEGIN TRANSACTION
INSERT INTO products (productname, price, categoryid)
VALUES
    ('AMD Radeon RX 580 8GB', 300, 4);
IF @@ERROR = 0
    COMMIT;
ELSE
    ROLLBACK;

-- SHOWPLAN_