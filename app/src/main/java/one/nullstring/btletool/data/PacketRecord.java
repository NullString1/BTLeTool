package one.nullstring.btletool.data;

import com.google.gson.annotations.SerializedName;

public class PacketRecord {
    @SerializedName("header")
    private RawPacketHeader header;
    @SerializedName("hci_header")
    private BluetoothHCIHeader hciHeader;
    @SerializedName("l2cap_header")
    private L2CAPacketHeader l2capHeader;
    @SerializedName("att_header")
    private ATTHeader attHeader;
    @SerializedName("packet_data")
    private byte[] packetData;

    private String packetDataString;
    @SerializedName("packet_number")
    private int packetNumber;
    public String getPacketDataString() { return packetDataString; }
    public void setPacketDataString(String packetDataString) { this.packetDataString = packetDataString; }
    public RawPacketHeader getHeader() { return header; }
    public void setHeader(RawPacketHeader header) { this.header = header; }
    public BluetoothHCIHeader getHciHeader() { return hciHeader; }
    public void setHciHeader(BluetoothHCIHeader hciHeader) { this.hciHeader = hciHeader; }
    public L2CAPacketHeader getL2capHeader() { return l2capHeader; }
    public void setL2capHeader(L2CAPacketHeader l2capHeader) { this.l2capHeader = l2capHeader; }
    public ATTHeader getAttHeader() { return attHeader; }
    public void setAttHeader(ATTHeader attHeader) { this.attHeader = attHeader; }
    public byte[] getPacketData() { return packetData; }
    public void setPacketData(byte[] packetData) { this.packetData = packetData; }
    public int getPacketNumber() { return packetNumber; }
    public void setPacketNumber(int packetNumber) { this.packetNumber = packetNumber; }
}