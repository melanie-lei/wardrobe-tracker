package ui;

import java.awt.Color;


public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to my project!");
        new WardrobeTracker();

        Color myColor = Color.blue;
        String colorS = String.valueOf(myColor.getRGB());
        Color c = new Color(Integer.parseInt(colorS));
        
        System.out.println(colorS);
    }
}
