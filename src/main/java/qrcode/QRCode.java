package qrcode;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author hassanzmn
 */
public class QRCode implements Serializable {
    private final IntegerProperty size = new SimpleIntegerProperty();
    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>();
    private final ObjectProperty<Color> foregroundColor = new SimpleObjectProperty<>();
    private final StringProperty content = new SimpleStringProperty();
    private final ObjectProperty<Image> initLogo = new SimpleObjectProperty<>();

    public QRCode() {
    }

    public IntegerProperty sizeProperty() {
        return this.size;
    }

    public int getSize() {
        return this.size.getValue();
    }

    public void setSize(int xy) {
        this.size.setValue(xy);
    }

    public ObjectProperty<Color> backgroundColorProperty() {
        return this.backgroundColor;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor.getValue();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor.setValue(color);
    }

    public ObjectProperty<Color> foregroundColorProperty() {
        return this.foregroundColor;
    }

    public Color getForegroundColor() {
        return this.foregroundColor.getValue();
    }

    public void setForegroundColor(Color color) {
        this.foregroundColor.setValue(color);
    }

    public StringProperty contentProperty() {
        return this.content;
    }

    public String getContent() {
        return this.content.getValue();
    }

    public void setContent(String content) {
        this.content.setValue(content);
    }

    public ObjectProperty<Image> initLogoProperty() {
        return this.initLogo;
    }

    public Image getInitLogo() {
        return this.initLogo.getValue();
    }

    public void setInitLogo(Image logo) {
        this.initLogo.setValue(logo);
    }
}
