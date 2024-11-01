package com.devicemanage;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CameraCapture {

    public static void main(String[] args) throws IOException {
        // Get the default webcam
        Webcam webcam = Webcam.getDefault();

        // Open the webcam
        webcam.open();

        // Capture an image
        BufferedImage image = webcam.getImage();

        // Save the image to a file
        ImageIO.write(image, "PNG", new File("captured_image.png"));

        // Close the webcam
        webcam.close();
    }
}