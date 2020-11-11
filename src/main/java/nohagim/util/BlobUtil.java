package nohagim.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BlobUtil {

    public static byte[] getBytes(String path){
        File file = new File(path);
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
