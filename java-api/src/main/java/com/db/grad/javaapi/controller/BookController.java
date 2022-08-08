package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.ErrorConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.ErrorResponse;
import com.db.grad.javaapi.model.book.Book;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URIConstants.API_V1)
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(URIConstants.GET_BOOKS_BY_USER_ID)
    public ResponseEntity<?> getBooksByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        try {
            log.info("Fetch Books Request For User ID: {}", userId);
            List<Book> books = bookService.getBooksByUserId(userId);
            return ResponseEntity.ok().body(books);
        }
        catch (ResourceNotFoundException E) {
            log.error(ErrorConstants.FETCH_BOOKS_BY_USER_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping(URIConstants.GET_TRADES_BY_BOOK_ID)
    public ResponseEntity<?> getTradesByBookId(@PathVariable(value = "bookId") Long bookId) throws ResourceNotFoundException {
        try {
            log.info("Fetch Trades Request For Book ID: {}", bookId);
            List<Trade> trades = bookService.getTradesByBookId(bookId);
            return ResponseEntity.ok().body(trades);
        }
        catch (ResourceNotFoundException E) {
            log.error(ErrorConstants.FETCH_TRADES_BY_BOOK_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
