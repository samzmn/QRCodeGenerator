package qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

/**
 *
 * @author hassanzmn
 */
public class ComplexQRCode {

    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }
    public static int getHexColor(Color color) {
        int a = (Integer.parseInt(format(color.getOpacity()), 16)) & 0xff;
        int r = (Integer.parseInt(format(color.getRed()), 16)) & 0xff;
        int g = (Integer.parseInt(format(color.getGreen()), 16)) & 0xff;
        int b = (Integer.parseInt(format(color.getBlue()), 16)) & 0xff;
        int argb = (a << 24) | (r << 16) | (g << 8) | b;
        return argb;
    }

    public static Image generate(QRCode qrcode) {
        // Create new configuration that specifies the error correction
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Image finalImg = null;

        try {
            // Create a qr code with the qrcode object
            bitMatrix = writer.encode(qrcode.getContent(), BarcodeFormat.QR_CODE, qrcode.getSize(), qrcode.getSize(), hints);

            //set qrcode's colors
            getHexColor(qrcode.getForegroundColor());
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(getHexColor(qrcode.getForegroundColor()), getHexColor(qrcode.getBackgroundColor()));

            // Load QR image
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

            if (qrcode.getInitLogo() != null) {
                // Load logo image
                BufferedImage overly = SwingFXUtils.fromFXImage(qrcode.getInitLogo(), null);

                // Calculate the delta height and width between QR code and logo
                int deltaHeight = qrImage.getHeight() - overly.getHeight();
                int deltaWidth = qrImage.getWidth() - overly.getWidth();

                // Initialize combined image
                BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = (Graphics2D) combined.getGraphics();

                // Write QR code to new image at position 0/0
                g.drawImage(qrImage, 0, 0, null);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

                // Write logo into combine image at position (deltaWidth / 2) and
                // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
                // the same space for the logo to be centered
                g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

                // Write combined image as PNG to OutputStream
                finalImg = SwingFXUtils.toFXImage(combined, null);
            } else {
                finalImg = SwingFXUtils.toFXImage(qrImage, null);
            }

            return finalImg;
            // Store Image
            //Files.copy(new ByteArrayInputStream(os.toByteArray()), Paths.get("qrcode" + ".png"), StandardCopyOption.REPLACE_EXISTING);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    private final String DIR = "/directory/to/save/images";
    private final String ext = ".png";
    private final String LOGO = "logo_url";
    private final String CONTENT = "some content here";
    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    public void generate() {
        // Create new configuration that specifies the error correction
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            // init directory
            cleanDirectory(DIR);
            initDirectory(DIR);
            // Create a qr code with the url as content and a size of WxH px
            bitMatrix = writer.encode(CONTENT, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

            // Load QR image
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());

            // Load logo image
            BufferedImage overly = getOverly(LOGO);

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = qrImage.getHeight() - overly.getHeight();
            int deltaWidth = qrImage.getWidth() - overly.getWidth();

            // Initialize combined image
            BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();

            // Write QR code to new image at position 0/0
            g.drawImage(qrImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Write logo into combine image at position (deltaWidth / 2) and
            // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
            // the same space for the logo to be centered
            g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            // Write combined image as PNG to OutputStream
            ImageIO.write(combined, "png", os);
            // Store Image
            Files.copy(new ByteArrayInputStream(os.toByteArray()), Paths.get(DIR + generateRandomTitle(new Random(), 9) + ext), StandardCopyOption.REPLACE_EXISTING);

        } catch (WriterException e) {
            e.printStackTrace();
            //LOG.error("WriterException occured", e);
        } catch (IOException e) {
            e.printStackTrace();
            //LOG.error("IOException occured", e);
        }
    }

    private BufferedImage getOverly(String LOGO) throws IOException {
        URL url = new URL(LOGO);
        return ImageIO.read(url);
    }

    private void initDirectory(String DIR) throws IOException {
        Files.createDirectories(Paths.get(DIR));
    }

    private void cleanDirectory(String DIR) {
        try {
            Files.walk(Paths.get(DIR), FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            // Directory does not exist, Do nothing
        }
    }

    private MatrixToImageConfig getMatrixConfig() {
        // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(ComplexQRCode.Colors.WHITE.getArgb(), ComplexQRCode.Colors.ORANGE.getArgb());
    }

    private String generateRandomTitle(Random random, int length) {
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public enum Colors {

        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFF4B13D),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        Colors(final int argb) {
            this.argb = argb;
        }

        public int getArgb() {
            return argb;
        }
    }
*/
}
