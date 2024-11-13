package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Scanner;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Clothing;
import model.Clothing.ClothingType;
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
    private boolean isViewClothing;
    private Clothing currentClothing;
    private WardrobeFrame wf;
    private JTextField nameField;
    private JTextField descField;
    private JComboBox<ClothingType> typeField;
    private JColorChooser colourField;

    WardrobePanel(Wardrobe wd, WardrobeFrame wf) {
        super();
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.white);
        this.wardrobe = wd;
        this.wf = wf;
    }

    // EFFECTS: Selects the view clothing panel
    public void changeClothing(Clothing c) {
        isViewClothing = true;
        currentClothing = c;
    }

    // EFFECTS: Selects the add clothing panel
    public void addClothingPanel() {
        isViewClothing = false;
        removeAll();
        nameField = new JTextField("Name");
        descField = new JTextField("Description");
        add(nameField);
        add(descField);
        typeField = new JComboBox<>();
        typeField.addItem(ClothingType.TOP);
        typeField.addItem(ClothingType.BOTTOMS);
        typeField.addItem(ClothingType.JACKET);
        typeField.addItem(ClothingType.SHOES);
        typeField.addItem(ClothingType.HEADWEAR);
        typeField.addItem(ClothingType.ACCESSORY);
        add(typeField);
        colourField = new JColorChooser();
        add(colourField);
        addClothingButton();
        revalidate();
    }

    // EFFECTS: Adds new clothing
    private void addClothingButton() {
        JButton button = new JButton("Add");
        button.setActionCommand("add");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.addClothing(new Clothing((ClothingType) typeField.getSelectedItem(), colourField.getColor(), nameField.getText(), descField.getText(), 2));
                wf.updateClothingList();
                addClothingPanel();
			}
        });

        add(button);
    }

     // EFFECTS: initializes the instances used for the wardrobe panel
    private void init() {
        isRunning = true;
        idTracker = 0;
        wardrobeSaver = new WardrobeSaver();
        isViewClothing = true;
    }

    @Override
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		drawWardrobe(g);
		repaint();

	}

    // EFFECTS: Draws wardrobe menu
    private void drawWardrobe(Graphics g) {
        if (isViewClothing) { 
            removeAll();
            drawSingleClothing(g);
        } else {
            // drawChooseClothing(g);
        }
    }

    private void drawChooseClothing(Graphics g) {
        
    }

    // EFFECTS: Draws a single clothing
    private void drawSingleClothing(Graphics g) {
        if (currentClothing != null) {
            g.setColor(currentClothing.getColour());
            g.fillRect(10, 20, 50, 50);
            g.drawString(currentClothing.getName(), 100, 20);
            g.drawString(currentClothing.getDescription(), 200, 20);
            g.drawString(currentClothing.getClothingType().toString(), 20, 200);
        }
        
    }

}