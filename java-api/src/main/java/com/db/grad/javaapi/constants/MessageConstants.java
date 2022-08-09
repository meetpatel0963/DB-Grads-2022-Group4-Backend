package com.db.grad.javaapi.constants;

public class MessageConstants {

    // Books Messages
    public static final String NO_BOOK_FOUND_FOR_GIVEN_ID = "No Book Found For Given ID.";
    public static final String NO_BOOKS_FOUND_FOR_GIVEN_USER_ID = "No Books Found For The Given User ID.";
    public static final String NO_TRADES_FOUND_FOR_GIVEN_BOOK_ID = "No Trades Found For The Given Book ID.";
    public static final String FETCH_BOOKS_BY_USER_ID_FAILED = "Fetch Books By User ID Failed. Reason: ";
    public static final String FETCH_TRADES_BY_BOOK_ID_FAILED = "Fetch Trades By Book ID Failed. Reason: ";

    // Trades Messages
    public static final String NO_TRADE_FOUND_FOR_GIVEN_ID = "No Trade Found For Given Trade ID.";
    public static final String FETCH_TRADE_BY_ID_FAILED = "Fetch Trade By ID Failed. Reason: ";
    public static final String FETCH_SECURITY_FOR_TRADE_FAILED = "Fetch Security For Trade Failed. Reason: ";
    public static final String ADD_TRADE_FAILED = "Add Trade Failed. Reason: ";
    public static final String UPDATE_TRADE_FAILED = "Update Trade Failed. Reason: ";

    // Auth Messages
    public static final String INVALID_USERNAME_OR_PASSWORD = "Invalid Username or Password!";

    // Users Messages
    public static final String USERNAME_ALREADY_IN_USE = "Username is already taken!";
    public static final String EMAIL_ALREADY_IN_USE = "Email Address already in use!";
    public static final String USER_REGISTER_SUCCESS = "User Registered Successfully.";
    public static final String NO_USER_FOUND_FOR_GIVEN_USER_ID = "No USer Found For Given User ID.";
    public static final String NO_USER_FOUND_FOR_GIVEN_USERNAME = "No USer Found For Given Username.";
    public static final String GET_USER_BY_ID_FAILED = "Get User By ID Failed. Reason: ";
    public static final String GET_USER_PROFILE_BY_USERNAME_FAILED = "Get User Profile By Username Failed. Reason: ";

    // PreAuthorize
    public static final String USER = "hasAuthority('USER')";
    public static final String ADMIN = "hasAuthority('ADMIN')";

    public static final String USER_ADMIN = "hasAuthority('USER') or hasAuthority('ADMIN')";

    // Security Messages
    public static final String NO_SECURITIES_FOUND = "No Securities Found.";
    public static final String NO_SECURITY_FOUND_FOR_GIVEN_ID = "No Securities Found For The Given ID.";
    public static final String FETCH_SECURITY_BY_ID_FAILED = "Fetch Security By Security ID Failed. Reason: ";
    public static final String UPDATE_SECURITY_FAILED = "Update Security Failed. Reason: ";
    public static final String DELETE_SECURITY_FAILED = "Delete Security Failed. Reason: ";
    public static final String FETCH_SECURITIES_BY_USER_ID_FAILED = "Fetch Securities By User ID Failed. Reason: ";
    public static final String FETCH_TRADES_FOR_SECURITY_ID_FAILED = "Fetch Trades For Security ID Failed. Reason: ";
    public static final String NO_TRADES_FOUND_FOR_GIVEN_SECURITY_ID = "No Trades Found For The Given Security ID.";
    public static final String FETCH_SECURITY_BY_DATE_RANGE_FAILED = "Fetch Security By Date Range Failed. Reason: ";
    public static final String FETCH_SECURITY_POST_MATURITY_FAILED = "Fetch Security Post Maturity Failed. Reason: ";

    // CounterParty Messages
    public static final String NO_COUNTERPARTY_FOUND_FOR_GIVEN_ID = "No CounterParty Found For Given ID.";

}
