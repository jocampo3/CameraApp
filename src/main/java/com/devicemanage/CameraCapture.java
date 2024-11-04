package com.devicemanage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;

public class CameraCapture {

    public static void main(String[] args) {

        // Create new JFrame to contain both
        // the "Capture Image" button and 
        // the captured image.
        JFrame frame = new JFrame("Camera Capture");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create new JPanel w/ a button.
        JPanel panel = new JPanel(new BorderLayout());

        JButton captureButton = new JButton("Capture Image");
        panel.add(captureButton, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        // Message dialog that tells the user
        // that their image has been captured.
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Image Captured!");
            }
        });
        
        // Add camera capture feature 
        // to the button.
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Webcam webcam = Webcam.getDefault(); // Get default webcam.
                    if (webcam != null) {
                        webcam.open(); // Open webcam.
                        BufferedImage image = webcam.getImage(); // Capture image.
                        webcam.close(); // Close webcam.

                        // Save captured image.
                        ImageIO.write(image, "PNG", new File("captured_image.png"));

                        imageLabel.setIcon(new javax.swing.ImageIcon(image));

                    } else {
                        // Message dialog if no webcam
                        // is detected.
                        System.out.println("No webcam detected");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
