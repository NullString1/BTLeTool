package one.nullstring.btsnoop_parser;

import com.google.gson.annotations.SerializedName;

public class RawPacketHeader {
    @SerializedName("original_length")
    private int originalLength;
    @SerializedName("included_length")
    private int includedLength;
    @SerializedName("packet_flags")
    private int packetFlags;
    @SerializedName("cumulative_drops")
    private int cumulativeDrops;
    @SerializedName("timestamp_milliseconds")
    private long timestampMilliseconds;
    public int getOriginalLength() { return originalLength; }
    public void setOriginalLength(int originalLength) { this.originalLength = originalLength; }
    public int getIncludedLength() { return includedLength; }
    public void setIncludedLength(int includedLength) { this.includedLength = includedLength; }
    public int getPacketFlags() { return packetFlags; }
    public void setPacketFlags(int packetFlags) { this.packetFlags = packetFlags; }
    public int getCumulativeDrops() { return cumulativeDrops; }
    public void setCumulativeDrops(int cumulativeDrops) { this.cumulativeDrops = cumulativeDrops; }
    public long getTimestampMilliseconds() { return timestampMilliseconds; }
    public void setTimestampMilliseconds(long timestampMilliseconds) { this.timestampMilliseconds = timestampMilliseconds; }
}