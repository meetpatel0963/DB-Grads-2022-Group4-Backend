package com.db.grad.javaapi.constants;

public class MessageConstants {

    // Books Error Messages
    public static final String NO_BOOKS_FOUND_FOR_GIVEN_USER_ID = "No Books Found For The Given User ID.";
    public static final String NO_TRADES_FOUND_FOR_GIVEN_BOOK_ID = "No Trades Found For The Given Book ID.";
    public static final String FETCH_BOOKS_BY_USER_ID_FAILED = "Fetch Books By User ID Failed. Reason: ";
    public static final String FETCH_TRADES_BY_BOOK_ID_FAILED = "Fetch Trades By Book ID Failed. Reason: ";

    // Trades Error Messages
    public static final String NO_TRADE_FOUND_FOR_GIVEN_ID = "No Trade Found For Given Trade ID.";
    public static final String FETCH_TRADE_BY_ID_FAILED = "Fetch Trade By ID Failed. Reason: ";
    public static final String UPDATE_TRADE_FAILED = "Update Trade Failed. Reason: ";

    // Users Error Messages
    public static final String USERNAME_ALREADY_IN_USE = "Username is already taken!";
    public static final String EMAIL_ALREADY_IN_USE = "Email Address already in use!";
    public static final String USER_REGISTER_SUCCESS = "User registered successfully";

    // PreAuthorize
    public static final String USER = "hasAuthority('USER')";
    public static final String ADMIN = "hasAuthority('ADMIN')";

    public static final String USER_ADMIN = "hasAuthority('USER') or hasAuthority('ADMIN')";
}
