package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.book.Book;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.repository.BookRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByUserId(@NonNull final Long userId) throws ResourceNotFoundException {
        List<Book> books = bookRepository.getBooksByUserId(userId);
        if (books == null || books.isEmpty()) {
            throw new ResourceNotFoundException(MessageConstants.NO_BOOKS_FOUND_FOR_GIVEN_USER_ID);
        }
        return books;
    }

    public List<Trade> getTradesByBookId(@NonNull final Long bookId) throws ResourceNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADES_FOUND_FOR_GIVEN_BOOK_ID));
        return new ArrayList<>(book.getTrades());
    }

}
