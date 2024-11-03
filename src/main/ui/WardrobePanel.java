package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Clothing;
import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

public class WardrobePanel extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private Wardrobe wardrobe;
    private boolean isRunning;
    private int idTracker;
    private WardrobeSaver wardrobeSaver;
    private boolean isClothing;

    WardrobePanel(Wardrobe wd) {
        super();
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.white);
        this.wardrobe = wd;
    }

     // EFFECTS: initializes the instances used for the wardrobe panel
    private void init() {
        isRunning = true;
        idTracker = 0;
        wardrobeSaver = new WardrobeSaver();
        isClothing = true;
    }

    @Override
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		drawWardrobe(g);
		

	}

    private void drawWardrobe(Graphics g) {
        if (isClothing) {
            
            drawClothing(g);
        }
    }

    private void drawClothing(Graphics g) {
        for (Clothing c : wardrobe.getClothing()) {
            drawSingleClothing(c, g);
        }
    }

    private void drawSingleClothing(Clothing c, Graphics g) {
        
    }

}