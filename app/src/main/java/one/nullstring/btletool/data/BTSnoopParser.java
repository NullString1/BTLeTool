package one.nullstring.btletool.data;

import com.google.gson.Gson;

import java.util.Comparator;

public class BTSnoopParser {

    static {
        System.loadLibrary("btsnoop_parser");
    }
    private static native String parse(byte[] bytes);
    public static native void log(String text);

    public static BTSnoopFile parseBTSnoopFile(byte[] bytes) {
        String parsedData = parse(bytes);
        Gson gson = new Gson();
        BTSnoopFile btSnoopFile = gson.fromJson(parsedData, BTSnoopFile.class);
        btSnoopFile.getPackets().forEach(packetRecord -> packetRecord.setPacketDataString(new String(packetRecord.getPacketData())));
        btSnoopFile.getPackets().sort(Comparator.comparingLong(packetRecord -> packetRecord.getHeader().getTimestampMilliseconds()));
        return btSnoopFile;
    }

}
