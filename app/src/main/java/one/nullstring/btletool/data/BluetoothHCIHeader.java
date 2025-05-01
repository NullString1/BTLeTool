package one.nullstring.btletool.data;

import com.google.gson.annotations.SerializedName;

public class BluetoothHCIHeader {
    @SerializedName("hci_packet_type")
    private HciPacketType hciPacketType;
    @SerializedName("command")
    private short command;
    @SerializedName("data_total_length")
    private short dataTotalLength;
    public HciPacketType getHciPacketType() { return hciPacketType; }
    public void setHciPacketType(HciPacketType hciPacketType) { this.hciPacketType = hciPacketType; }
    public short getCommand() { return command; }
    public void setCommand(short command) { this.command = command; }
    public short getDataTotalLength() { return dataTotalLength; }
    public void setDataTotalLength(short dataTotalLength) { this.dataTotalLength = dataTotalLength; }
}