package nohagim.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nohagim.util.BlobUtil;
import nohagim.util.PDFUtil;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;

public class PdfViewer implements Initializable {


    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            BufferedImage bi = PDFUtil.PDFtoJPG(BlobUtil.getBytes("src/main/resources/nohagim/testfiles/sample.pdf")).get(0);
            Image image = SwingFXUtils.toFXImage(bi, null);
            imageView.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
