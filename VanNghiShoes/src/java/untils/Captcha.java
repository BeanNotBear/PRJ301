/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;

import java.util.Random;

/**
 *
 * @author nghin
 */
public class Captcha {
    public static String generateCaptcha() {
        // Define characters allowed in the CAPTCHA
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a StringBuilder to store the generated CAPTCHA
        StringBuilder captcha = new StringBuilder();

        // Create an instance of Random class
        Random random = new Random();

        // Generate the CAPTCHA
        for (int i = 0; i < 5; i++) {
            // Generate a random index within the range of allowedChars
            int index = random.nextInt(allowedChars.length());
            
            // Append the character at the generated index to the CAPTCHA
            captcha.append(allowedChars.charAt(index));
        }

        // Convert the StringBuilder to a String and return the CAPTCHA
        return captcha.toString();
    }
}
