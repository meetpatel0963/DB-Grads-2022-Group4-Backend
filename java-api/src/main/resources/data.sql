INSERT INTO Books (id, name) VALUES (1, 'Book1');
INSERT INTO Books (id, name) VALUES (2, 'Book2');

INSERT INTO Users (id, name, username, email) VALUES (1, 'User1', 'u1', 'user1@gmail.com');
INSERT INTO Users (id, name, username, email) VALUES (2, 'User2', 'u2', 'user2@gmail.com');

INSERT INTO Book_User (book_id, user_id) VALUES (1, 2);
INSERT INTO Book_User (book_id, user_id) VALUES (2, 1);

INSERT INTO Roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO Roles (id, name) VALUES (2, 'USER');

INSERT INTO User_Role (user_id, role_id) VALUES (1, 1);
INSERT INTO User_Role (user_id, role_id) VALUES (2, 2);

INSERT INTO Security (id, isin, cusip, issuer, maturity_date, coupon, type, face_value, status)
VALUES (1, 123456789, 123456, 1234, '2020-10-19', 10, 'Bond', 10000, 'completed');
INSERT INTO Security (id, isin, cusip, issuer, maturity_date, coupon, type, face_value, status)
VALUES (2, 987654321, 654321, 4321, '2023-10-19', 9, 'Bond', 12000, 'completed');

INSERT INTO Counter_Party (id, name) VALUES (1, 'counter_party_1');
INSERT INTO Counter_Party (id, name) VALUES (2, 'counter_party_2');

INSERT INTO Trades (id, book_id, counterparty_id, security_id, quantity, status, price, buy_sell, trade_date, settlement_date)
VALUES (1, 1, 1, 1, 100, 'COMPLETED', 80000, 'BUY', '2022-08-05', '2022-08-10');
INSERT INTO Trades (id, book_id, counterparty_id, security_id, quantity, status, price, buy_sell, trade_date, settlement_date)
VALUES (2, 2, 2, 2, 200, 'IN_PROGRESS', 84000, 'SELL', '2023-08-05', '2023-08-10');
INSERT INTO Trades (id, book_id, counterparty_id, security_id, quantity, status, price, buy_sell, trade_date, settlement_date)
VALUES (3, 2, 2, 1, 200, 'IN_PROGRESS', 90000, 'SELL', '2023-08-05', '2023-08-10');