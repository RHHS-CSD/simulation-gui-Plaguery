/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {

    // Method to load and resize an image
    public static BufferedImage loadAndResizeImage(String path, int newWidth, int newHeight) {
        BufferedImage originalImage = null;
        try {
            // Load the original image
            originalImage = ImageIO.read(new File(path));
            // Create a new buffered image with the specified size
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            // Enable anti-aliasing for smoother edges
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Draw the original image scaled to the new size
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose(); // Dispose of the graphics context
            return resizedImage; // Return the resized image
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions as needed
            return null; // Return null in case of an error
        }
    }
    // Method to load and resize an image

    public static BufferedImage loadImage(String path) {
        BufferedImage originalImage = null;
        try {
            // Load the original image
            return ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions as needed
            return null; // Return null in case of an error
        }
    }

    public static Image loadAndResizeQuick(String path, int newWidth, int newHeight) {
        Image img1;
        try {
            // Load the original image
            img1 = ImageIO.read(new File(path));
            img1 = img1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions as needed
            return null; // Return null in case of an error
        }
        return img1;
    }
}
