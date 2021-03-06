package com.baturayucer.reactivecrudapi.constant;

public class ItemConstants {
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String LEGACY_CONTROLLER_V1 = "/legacyController/v1";
    public static final String FUNCTIONAL_ROUTER_V1 = "/functionalRouter/v1";
    public static final String ITEMS_ALL = "/items/all";
    public static final String FIND_ONE = "/items/findOne";
    public static final String FIND_BY_DESC = "/items/findByDesc";
    public static final String CREATE_ITEM = "/createItem";

    private ItemConstants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a constant class.");
    }
}