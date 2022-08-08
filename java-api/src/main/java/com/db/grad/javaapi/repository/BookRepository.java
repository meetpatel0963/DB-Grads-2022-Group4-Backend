package com.db.grad.javaapi.repository;

import com.db.grad.javaapi.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b INNER JOIN b.users u WHERE u.id = :userId")
    List<Book> getBooksByUserId(Long userId);

}
