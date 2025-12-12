package qrcode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author hassanzmn
 */
public class QRCodeDemo {
    /*
    public static void main(String[] args) {
        // The data that the QR code will contain
        String data = "www.nightskydesign.ir";

        // The path where the image will get saved
        String path = "demo.png";

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        QRCode.createQR(data, Path.of(path), "png", charset, hashMap, 200, 200);
        System.out.println("QR Code Generated!!! ");

        // Path where the QR code is saved
        String filePath = "F:\\JAVA\\My programs\\javaSE\\QRCodeDemo\\demo.png";

        // Encoding charset
        //String charset = "UTF-8";
        //Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType,ErrorCorrectionLevel>();
        //hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        System.out.println("QRCode output: " + QRCode.readQR(filePath, charset, hashMap));
    }
*/
}
