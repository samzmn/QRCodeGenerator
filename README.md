# QRCodeGenerator

A JavaFX application for generating QR codes using the ZXing library.

## Features

- Generate simple QR codes
- Generate complex QR codes with custom settings
- GUI interface built with JavaFX and FXML

## Project Structure

- `src/main/java/qrcode/`: Main Java source files
  - `App.java`: Main application class
  - `MainWindowController.java`: Controller for the main window
  - `QRCode.java`: QR code generation utilities
  - `SimpleQRCode.java`: Simple QR code generator
  - `ComplexQRCode.java`: Advanced QR code generator
  - `QRCodeDemo.java`: Demo class for QR code generation
  - `util/ImageUtilis.java`: Image utility functions
- `src/main/resources/qrcode/`: FXML resources
  - `MainWindow.fxml`: Main window layout
- `src/test/java/`: Test files

## Dependencies

- JavaFX 20.0.1
- ZXing Core 3.5.2
- ZXing JavaSE 3.5.2

## Building and Running

### Prerequisites

- Java 20 or higher
- Maven 3.6 or higher

### Build

```bash
mvn clean compile
```

### Run

```bash
mvn javafx:run
```

### Debug

```bash
mvn javafx:run@debug
```

## Usage

1. Launch the application using the run command above.
2. Enter the data you want to encode in the QR code.
3. Customize settings if needed (for complex QR codes).
4. Generate and save the QR code image.

## Author

- **Name**: [Sam Zamani]
- **Email**: [sam.zmn99@gmail.com]
- **Website**: [www.pixisky.com](http://www.pixisky.com)

## License

This project is licensed under the MIT License.