package one.nullstring.btletool.data;

import com.google.gson.Gson;

public class BTSnoopParser {

    static {
        System.loadLibrary("btsnoop_parser");
    }
    private static native String parse(byte[] bytes, boolean write_and_notify_only, boolean sort_by_timestamp);
    public static native void log(String text);

    public static BTSnoopFile parseBTSnoopFile(byte[] bytes) {
        String parsedData = parse(bytes, true, true);
        Gson gson = new Gson();
        return gson.fromJson(parsedData, BTSnoopFile.class);
    }

}
