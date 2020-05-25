package com.baturayucer.reactiveclient.constant;

/**
 * @author baturayucer.
 */
public class ReactiveClientConstants {

    public static final String API_URL = "http://localhost:8080";
    public static final String REACTIVE_CLIENT_V1 = "/reactiveClient/v1";
    public static final String LEGACY_CONTROLLER_V1 = "/legacyController/v1";
    public static final String ITEMS_ALL = "/items/all";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String FIND_ONE = "/items/findOne";
    public static final String FIND_BY_DESC = "/items/findByDesc";
    public static final String CREATE_ITEM = "/createItem";

    private ReactiveClientConstants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a constant class.");
    }
}