package one.nullstring.btletool.data;

public class ATTHeader {
    private ATTCommand command;
    private short handle;
    private byte[] data;

    public ATTCommand getCommand() { return command; }
    public void setCommand(ATTCommand command) { this.command = command; }
    public short getHandle() { return handle; }
    public void setHandle(short handle) { this.handle = handle; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
}