package one.nullstring.btletool.data;

import com.google.gson.annotations.SerializedName;

public class BluetoothHCIHeader {
    @SerializedName("hci_packet_type")
    private HciPacketType hciPacketType;
    @SerializedName("hci_handle")
    private short hciHandle;
    @SerializedName("data_total_length")
    private short dataTotalLength;
    public HciPacketType getHciPacketType() { return hciPacketType; }
    public void setHciPacketType(HciPacketType hciPacketType) { this.hciPacketType = hciPacketType; }
    public short getHciHandle() { return hciHandle; }
    public void setHciHandle(short hciHandle) { this.hciHandle = hciHandle; }
    public short getDataTotalLength() { return dataTotalLength; }
    public void setDataTotalLength(short dataTotalLength) { this.dataTotalLength = dataTotalLength; }
}