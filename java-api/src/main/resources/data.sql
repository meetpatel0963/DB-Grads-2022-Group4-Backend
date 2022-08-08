INSERT INTO Books (id, name) VALUES (1, 'Book1');
INSERT INTO Books (id, name) VALUES (2, 'Book2');

--INSERT INTO Users (id, name, email, role) VALUES (1, 'User1', 'user1@gmail.com', 'Developer');
--INSERT INTO Users (id, name, email, role) VALUES (2, 'User2', 'user2@gmail.com', 'Tester');
--
--INSERT INTO Book_User (book_id, user_id) VALUES (1, 2);
--INSERT INTO Book_User (book_id, user_id) VALUES (2, 1);

INSERT INTO Roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO Roles (id, name) VALUES (2, 'USER');

INSERT INTO Trades (id, book_id, counterparty_id, security_id, quantity, status, price, buy_sell, trade_date, settlement_date)
VALUES (1, 1, 1, 1, 100, 'COMPLETED', 80000, 'BUY', '2022-08-05', '2022-08-10');
INSERT INTO Trades (id, book_id, counterparty_id, security_id, quantity, status, price, buy_sell, trade_date, settlement_date)
VALUES (2, 2, 2, 2, 200, 'IN_PROGRESS', 84000, 'SELL', '2023-08-05', '2023-08-10');