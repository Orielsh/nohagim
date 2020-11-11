package nohagim.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PDFUtil {

    public static ArrayList<BufferedImage> PDFtoJPG(byte[] bytes) throws Exception {
        ArrayList<BufferedImage> pages = new ArrayList<>();
        PDDocument doc = PDDocument.load(bytes);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageSize = doc.getNumberOfPages();
        for (int i = 0; i < pageSize; i++) {
            pages.add(renderer.renderImage(i));
        }
        return pages;
    }

}
