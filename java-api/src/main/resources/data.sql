INSERT INTO Books (id, name) VALUES (1, 'Book1');
INSERT INTO Books (id, name) VALUES (2, 'Book2');

INSERT INTO Users (id, name, email, role) VALUES (1, 'User1', 'user1@gmail.com', 'Developer');
INSERT INTO Users (id, name, email, role) VALUES (2, 'User2', 'user2@gmail.com', 'Tester');

INSERT INTO Book_User (book_id, user_id) VALUES (1, 2);
INSERT INTO Book_User (book_id, user_id) VALUES (2, 1);