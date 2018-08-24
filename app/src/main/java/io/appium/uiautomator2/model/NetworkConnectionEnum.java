package io.appium.uiautomator2.model;

public enum NetworkConnectionEnum {
    AIRPLANE(1), WIFI(2), DATA(4), ALL(6), NONE(0), IN_VALID(null);
    private final Integer value;

    NetworkConnectionEnum(final Integer value) {
        this.value = value;
    }

    public static NetworkConnectionEnum getNetwork(int type) {
        switch (type) {
            case 0:
                return NONE;
            case 1:
                return AIRPLANE;
            case 2:
                return WIFI;
            case 4:
                return DATA;
            case 6:
                return ALL;
            default:
                return IN_VALID;
        }
    }

    public int getNetworkTypeValue() {
        return this.value;
    }

    public String getNetworkType() {
        switch (this.getNetworkTypeValue()) {
            case 0:
                return "NONE";
            case 1:
                return "AIRPLANE";
            case 2:
                return "WIFI Only";
            case 4:
                return "DATA Only";
            case 6:
                return "All Network ON";
        }
        return "UNKNOWN(" + this.getNetworkTypeValue() + ")";
    }

}
