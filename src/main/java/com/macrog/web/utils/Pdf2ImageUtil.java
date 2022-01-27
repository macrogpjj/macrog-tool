package com.macrog.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf转图片
 *
 * @author macrog
 * @date 2022-01-27 20:18
 */
@Slf4j
public class Pdf2ImageUtil {

    /**
     * dpi越大转换越清晰
     */
    private static final Integer DPI = 100;

    /**
     * 转换后的图片类型
     */
    private static final String IMG_TYPE = "png";

    public static List<byte[]> pdf2Image(byte[] fileContent) {
        List<byte[]> result = new ArrayList<>();
        try {
            PDDocument document = PDDocument.load(fileContent);

            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, DPI);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, IMG_TYPE, out);
                result.add(out.toByteArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        String pdfPath = "C:\\Users\\Administrator\\Desktop\\毕业证原件扫描图.PDF";
        File file = new File(pdfPath);

        FileInputStream in = new FileInputStream(file);

        byte[] bytes = new byte[in.available()];

        in.read(bytes);

        List<byte[]> imageByte = pdf2Image(bytes);

        String imagePath = "C:\\Users\\Administrator\\Desktop\\毕业证原件扫描图.png";

        for (int i = 0; i < imageByte.size(); i++) {
            ImageUtil.byte2iImage(imageByte.get(i), "C:\\Users\\Administrator\\Desktop\\" + i + ".png");
        }

    }

}
