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
    private Clothing currentClothing;

    WardrobePanel(Wardrobe wd) {
        super();
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.white);
        this.wardrobe = wd;
    }

    public void changeClothing(Clothing c) {
        isClothing = true;
        currentClothing = c;
    }

    public void addClothingPanel() {
        isClothing = false;
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
		repaint();

	}

    private void drawWardrobe(Graphics g) {
        if (isClothing) { 
            drawSingleClothing(g);
        }
    }


    private void drawSingleClothing(Graphics g) {
        if (currentClothing != null) {
            g.setColor(currentClothing.getColour());
            g.fillRect(10, 20, 50, 50);
        }
        
    }

}