package com.devicemanage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;

public class WebcamApp extends JFrame {
    private final JPanel mainPanel;
    private final JButton captureButton;
    private final JLabel imageLabel;
    private Webcam webcam;

    public WebcamApp() {
        // Set up the window
        setTitle("Webcam Capture App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        mainPanel = new JPanel(new BorderLayout());
        captureButton = new JButton("Capture Photo");
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(640, 480));

        // Add components to the frame
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(captureButton, BorderLayout.SOUTH);
        add(mainPanel);

        // Initialize webcam
        try {
            webcam = Webcam.getDefault();
            if (webcam != null) {
                webcam.setViewSize(new Dimension(640, 480));
                webcam.open();
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No webcam detected!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error initializing webcam: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add capture button functionality
        captureButton.addActionListener(e -> captureImage());

        // Set up window properties
        pack();
        setLocationRelativeTo(null);

        // Add window closing handler
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (webcam != null && webcam.isOpen()) {
                    webcam.close();
                }
            }
        });
    }

    private void captureImage() {
        if (webcam != null && webcam.isOpen()) {
            // Capture image from webcam
            BufferedImage image = webcam.getImage();
            
            // Display the captured image
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
            
            // Refresh the window
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            WebcamApp app = new WebcamApp();
            app.setVisible(true);
        });
    }
}