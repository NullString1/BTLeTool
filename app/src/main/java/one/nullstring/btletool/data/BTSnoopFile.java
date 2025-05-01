package one.nullstring.btletool.data;

import java.util.ArrayList;
import java.util.List;

public class BTSnoopFile {
    private FileHeader header;
    private List<PacketRecord> packets;

    public BTSnoopFile() {
        this.packets = new ArrayList<>();
    }

    public FileHeader getHeader() { return header; }
    public void setHeader(FileHeader header) { this.header = header; }
    public List<PacketRecord> getPackets() { return packets; }
    public void setPackets(List<PacketRecord> packets) { this.packets = packets; }
}