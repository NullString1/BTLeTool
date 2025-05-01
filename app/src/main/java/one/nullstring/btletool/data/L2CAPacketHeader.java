package one.nullstring.btletool.data;

import com.google.gson.annotations.SerializedName;

public class L2CAPacketHeader {
    private short length;
    @SerializedName("channel_id")
    private short channelId;

    // Getters and setters
    public short getLength() { return length; }
    public void setLength(short length) { this.length = length; }
    public short getChannelId() { return channelId; }
    public void setChannelId(short channelId) { this.channelId = channelId; }
}