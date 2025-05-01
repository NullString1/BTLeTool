package one.nullstring.btletool.data;


import com.google.gson.annotations.SerializedName;

public enum ATTCommand {
    @SerializedName("None")
    NONE(0x00),
    @SerializedName("WriteCommand")
    WRITE_COMMAND(0x52),
    @SerializedName("HandleValueNotification")
    HANDLE_VALUE_NOTIFICATION(0x1b);

    private final int value;

    ATTCommand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ATTCommand fromValue(int value) {
        for (ATTCommand cmd : ATTCommand.values()) {
            if (cmd.getValue() == value) {
                return cmd;
            }
        }
        return NONE;
    }
}