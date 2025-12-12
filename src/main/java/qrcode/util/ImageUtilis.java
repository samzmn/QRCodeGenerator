package qrcode.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author hassanzmn
 */
public class ImageUtilis {

    public static Image setSize(Image image, int targetWidth, int targetHeight) {

        // Get its width and height
        int sourceWidth = (int) image.getWidth();
        int sourceHeight = (int) image.getHeight();

        // Create a WritableImage object with the target size
        WritableImage target = new WritableImage(targetWidth, targetHeight);

        // Get the PixelReader of the source image
        PixelReader pr = image.getPixelReader();

        // Get the PixelWriter of the target image
        PixelWriter pw = target.getPixelWriter();

        // Scale the pixels from the source image to the target image
        for (int x = 0; x < targetWidth; x++) {
            for (int y = 0; y < targetHeight; y++) {
                // Compute the source coordinates
                int sx = x * sourceWidth / targetWidth;
                int sy = y * sourceHeight / targetHeight;

                // Get the color of the pixel at (sx, sy) in the source image
                Color color = pr.getColor(sx, sy);

                // Set the color of the pixel at (x, y) in the target image
                pw.setColor(x, y, color);
            }
        }

        return target;
    }
}
