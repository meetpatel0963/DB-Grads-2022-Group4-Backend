package com.db.grad.javaapi.constants;

public class ErrorConstants {

    // Books Error Messages
    public static final String NO_BOOKS_FOUND_FOR_GIVEN_USER_ID = "No Books Found For The Given User ID.";
    public static final String NO_TRADES_FOUND_FOR_GIVEN_BOOK_ID = "No Trades Found For The Given Book ID.";
    public static final String FETCH_BOOKS_BY_USER_ID_FAILED = "Fetch Books By User ID Failed. Reason: ";
    public static final String FETCH_TRADES_BY_BOOK_ID_FAILED = "Fetch Trades By Book ID Failed. Reason: ";

    // Trades Error Messages
    public static final String NO_TRADE_FOUND_FOR_GIVEN_ID = "No Trade Found For Given Trade ID.";
    public static final String FETCH_TRADE_BY_ID_FAILED = "Fetch Trade By ID Failed. Reason: ";
    public static final String UPDATE_TRADE_FAILED = "Update Trade Failed. Reason: ";

    // Security Error Messages
    public static final String NO_SECURITIES_FOUND = "No Securities Found.";
    public static final String NO_SECURITY_FOUND_FOR_GIVEN_ID = "No Securities Found For The Given Book ID.";
    public static final String FETCH_SECURITY_BY_ID_FAILED = "Fetch Security By Security ID Failed. Reason: ";
    public static final String UPDATE_SECURITY_FAILED = "Update Security Failed. Reason: ";
    public static final String DELETE_SECURITY_FAILED = "Delete Security Failed. Reason: ";
    public static final String FETCH_SECURITY_BY_DATERANGE_FAILED = "Fetch Security By Daterange Failed. Reason: ";
    
}
