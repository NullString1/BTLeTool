package one.nullstring.btsnoop_parser;

public class FileHeader {
    private byte[] identifier;
    private int version;
    private int data_link_type;

    public FileHeader() {
        this.identifier = new byte[8];
    }
    public byte[] getIdentifier() { return identifier; }
    public void setIdentifier(byte[] identifier) { this.identifier = identifier; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    public int getData_link_type() { return data_link_type; }
    public void setData_link_type(int data_link_type) { this.data_link_type = data_link_type; }
}