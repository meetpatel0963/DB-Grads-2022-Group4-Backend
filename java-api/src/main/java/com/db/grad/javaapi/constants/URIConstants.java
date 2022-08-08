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

    // Auth URIs
    public static final String SIGN_UP = "/auth/signup";
    public static final String SIGN_IN = "/auth/signin";

    // User URIs
    public static final String GET_USER_BY_ID = "/users/{id}";
    public static final String CHECK_USERNAME_AVAILABILITY = "/users/checkUsernameAvailability";
    public static final String CHECK_EMAIL_AVAILABILITY = "/users/checkEmailAvailability";
    public static final String GET_USER_PROFILE = "/users/username/{username}";

}
