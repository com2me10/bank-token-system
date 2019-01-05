package com.bank.constants;

import java.util.HashMap;
import java.util.Map;

public enum CustomerType {

    PREMIUM,
    REGULAR;

    static final Map<String, CustomerType> entityTypeMap = new HashMap() {
        {
            put("premium", PREMIUM);
            put("regular", REGULAR);
        }
    };

    public static String getEntityType(String entityType) {

        Object entityTypeObj = entityTypeMap.get(entityType.toLowerCase());
        return entityTypeObj.toString();
    }
}
