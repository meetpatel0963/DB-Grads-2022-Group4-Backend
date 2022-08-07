INSERT INTO dogs (id, name, age) VALUES (1, 'Einstein', 3);
INSERT INTO dogs (id, name, age) VALUES (2, 'Kaya', 5);

INSERT INTO book (id, bookname) VALUES (1, 'Book1');
INSERT INTO book (id, bookname) VALUES (2, 'Book2');

INSERT INTO Users (id, name, email, role) VALUES (1, 'Kushal', 'kushalnl2000@gmail.com', 'Developer');
INSERT INTO Users (id, name, email, role) VALUES (2, 'User2', 'user2@gmail.com', 'Tester');

INSERT INTO BookUser (bookid, userid) VALUES (1, 2);
INSERT INTO BookUser (bookid, userid) VALUES (2, 1);

INSERT INTO Security (id, isin, cusip, issuer, maturitydate, coupon, type, facevalue, status) 
VALUES (1, 123456789, 123456, 1234, '2020-10-19', 10, 'Bond', 10000, 'completed');
INSERT INTO Security (id, isin, cusip, issuer, maturitydate, coupon, type, facevalue, status) 
VALUES (2, 987654321, 654321, 4321, '2023-10-19', 9, 'Bond', 12000, 'completed');

INSERT INTO Counterparty (id, name) VALUES (1, 'Kushal');
INSERT INTO Counterparty (id, name) VALUES (2, 'Client2');

INSERT INTO Trade (id, bookid, counterpartyid, securityid, quantity, status, price, buysell, tradedate, settlementdate)
VALUES (1, 1, 1, 1, 100, 'Completed', 80000, 'buy', '2022-08-05', '2022-08-10');
INSERT INTO Trade (id, bookid, counterpartyid, securityid, quantity, status, price, buysell, tradedate, settlementdate)
VALUES (2, 2, 2, 2, 200, 'Inprogress', 84000, 'sell', '2023-08-05', '2023-08-10');
