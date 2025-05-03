package one.nullstring.btsnoop_parser;

import com.google.gson.annotations.SerializedName;

public enum HciPacketType {
    @SerializedName("None")
    NONE(0x00),
    @SerializedName("Command")
    COMMAND(0x01),
    @SerializedName("Event")
    EVENT(0x04),
    @SerializedName("ACLData")
    ACL_DATA(0x02),
    @SerializedName("SCOData")
    SCO_DATA(0x03);

    private final int value;

    HciPacketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static HciPacketType fromValue(int value) {
        for (HciPacketType type : HciPacketType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return NONE;
    }
}