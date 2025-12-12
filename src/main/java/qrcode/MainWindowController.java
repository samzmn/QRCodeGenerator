package qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import qrcode.util.ImageUtilis;

/**
 * FXML Controller class
 *
 * @author hassanzmn
 */
public class MainWindowController implements Initializable {
    @FXML
    private TextArea contentText;
    @FXML
    private ColorPicker bgColor;
    @FXML
    private ColorPicker fgColor;
    @FXML
    private Button uploadBtn;
    @FXML
    private CheckBox saveLogoCheckbox;
    @FXML
    private ImageView logoImg;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Label sizeLbl;
    @FXML
    private Button createBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private ImageView qrImg;

    //non FXML variables
    private final QRCode qrcode = new QRCode();
    private FileChooser uploadFileChooser = new FileChooser();
    private FileChooser saveFileChooser = new FileChooser();
    private Image logo = null;
    private Image qrCodeImg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sizeSlider.valueProperty().addListener(cl -> {
            sizeLbl.setText(String.format("%.0f * %.0f", sizeSlider.getValue(), sizeSlider.getValue()));
        });
        //configure the uploadFileChooser
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        uploadFileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        uploadFileChooser.setTitle("Select an Image File");

        //configure the saveFileChooser
        saveFileChooser.setTitle("Save the QR Code");
        saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    }

    @FXML
    private void uploadLogo() {
        File selectedFile = uploadFileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Create an image object from the file path
            logo = new Image(selectedFile.toURI().toString());

            // Set the image to the image view
            logoImg.setImage(logo);

            qrcode.setInitLogo(logo);
        }
    }

    @FXML
    private void deleteLogo() {
        logoImg.setImage(null);
        logo = null;
    }

    @FXML
    private void createQRCode() {
        if (contentText.getText() == null || contentText.getText().equals("")) {
            return;
        }

        int xy = (int) sizeSlider.getValue();
        Image resizedLogo = null;
        if (logo != null) {
            resizedLogo = ImageUtilis.setSize(logo, xy / 4, xy / 4);
        }

        qrcode.setInitLogo(resizedLogo);
        qrcode.setContent(contentText.getText());
        qrcode.setSize(xy);
        qrcode.setBackgroundColor(bgColor.getValue());
        qrcode.setForegroundColor(fgColor.getValue());

        qrCodeImg = ComplexQRCode.generate(qrcode);
        qrImg.setImage(qrCodeImg);
    }

    @FXML
    private void saveQRCode() {
        // Show the file chooser dialog and get the selected file
        File selectedFile = saveFileChooser.showSaveDialog(null);

        // Check if a file was selected
        if (selectedFile != null) {
            // Get the format name from the file extension
            String formatName = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);

            // Write the image to the file using ImageIO
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImg, null), formatName, selectedFile);
            } catch (IOException ex) {
                // TODO: handle exception here
            }
        }
    }

}
