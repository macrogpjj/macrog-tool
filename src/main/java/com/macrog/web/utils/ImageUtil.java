package com.macrog.web.utils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;

/**
 * @author macrog
 * @date 2022-01-27 20:39
 */
public class ImageUtil {

    public static void byte2iImage(byte[] data, String path) {
        if (data.length < 3 || "".equals(path)) {
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}
