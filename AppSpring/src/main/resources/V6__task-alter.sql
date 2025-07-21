BEGIN TRANSACTION

ALTER TABLE users
ADD email varchar(60);

-- ALTER TABLE sales
-- DROP CONSTRAINT FK__Sales__UserId__4316F928,
--     FK__Sales__ProductId__4222D4EF;

ALTER TABLE sales
    ADD CONSTRAINT FK_Sales_Users foreign key(UserId)
        REFERENCES users(UserId)
        ON DELETE CASCADE;

ALTER TABLE sales
    ADD CONSTRAINT FK_Sales_Products foreign key(ProductId)
        REFERENCES products(ProductId)
        ON DELETE CASCADE;

IF @@ERROR = 0
    COMMIT;
ELSE
    ROLLBACK;