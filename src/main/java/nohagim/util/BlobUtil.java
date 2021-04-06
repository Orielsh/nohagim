package nohagim.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BlobUtil {
    //todo: check if need to close file or only streams.
    public static byte[] getBytes(File file) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        }
        catch (IOException e){
            e.printStackTrace();//todo handle trough gui
        }
        return bytes;
    }
}
