package com.db.grad.javaapi.constants;

public class URIConstants {

    public static final String API_V1 = "/api/v1";

    // Books URIs
    public static final String GET_BOOKS_BY_USER_ID = "/books/{userId}";
    public static final String GET_TRADES_BY_BOOK_ID = "/books/trades/{bookId}";

    // Trade URIs
    public static final String GET_TRADE_BY_ID = "/trades/{tradeId}";
    public static final String ADD_TRADE = "/trades";
    public static final String UPDATE_TRADE = "/trades/{tradeId}";
    
    //Security URIs
    public static final String GET_ALL_SECURITY = "/security";
    public static final String GET_SECURITY_BY_ID = "/security/{securityId}";
    public static final String ADD_SECURITY = "/security";
    public static final String UPDATE_SECURITY = "/security/{securityId}";
    public static final String DELETE_SECURITY = "/security/{securityId}";
    public static final String GET_TRADES_BY_SECURITYID = "security/{securityId}/trades";
    public static final String GET_SECURITY_BY_DATERANGE = "/security/daterange/{date1}/{date2}";
}
