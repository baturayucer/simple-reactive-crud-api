package com.baturayucer.reactiveclient.constant;

/**
 * @author baturayucer.
 */
public class ReactiveClientConstants {

    public static final String API_URL = "http://localhost:8080";
    public static final String REACTIVE_CLIENT_V1 = "/reactiveClient/v1";
    public static final String ITEMS_ALL = "/items/all";

    private ReactiveClientConstants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a constant class.");
    }
}