package io.appium.uiautomator2.utils;

import java.util.Map;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class ModelValidators {
    public static String requireString(Object model, String fieldName) {
        Object value = getField(fieldName, model);
        if (value instanceof String) {
            return (String) value;
        }
        throw new IllegalArgumentException(
                String.format("The '%s' property must be set to a valid string. '%s' is given instead",
                        fieldName, value));
    }

    public static Double requireDouble(Object model, String fieldName) {
        Object value = getField(fieldName, model);
        if (value instanceof Double) {
            return (Double) value;
        }
        throw new IllegalArgumentException(
                String.format("The '%s' property must be set to a valid number. '%s' is given instead",
                        fieldName, value));
    }

    public static Integer requireInteger(Object model, String fieldName) {
        Object value = getField(fieldName, model);
        if (value instanceof Integer) {
            return (Integer) value;
        }
        throw new IllegalArgumentException(
                String.format("The '%s' property must be set to a valid integer number. '%s' is given instead",
                        fieldName, value));
    }

    @SuppressWarnings("rawtypes")
    public static Map requireMap(Object model, String fieldName) {
        Object value = getField(fieldName, model);
        if (value instanceof Map) {
            return (Map) value;
        }
        throw new IllegalArgumentException(
                String.format("The '%s' property must be set to a valid map. '%s' is given instead",
                        fieldName, value));
    }
}
