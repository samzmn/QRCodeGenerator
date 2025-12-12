package qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author hassanzmn
 */
public class SimpleQRCode {

    // Function to create the QR code
    public static void createQR(String data, Path path, String format,
            String charset, Map hashMap, int height, int width)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToPath(matrix, format, path);
    }

    // Function to read the QR file
    public static String readQR(String path, String charset, Map hashMap)
            throws FileNotFoundException, IOException, NotFoundException {

        BinaryBitmap binaryBitmap
                = new BinaryBitmap(new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                ImageIO.read(
                                        new FileInputStream(path)))));

        Result result = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }
}
