package ui;

import java.awt.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Clothing;
import model.Clothing.ClothingType;
import model.Wardrobe;

public class WardrobePanel extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private Wardrobe wardrobe;
    private int idTracker;
    private boolean isViewClothing;
    private Clothing currentClothing;
    private WardrobeFrame wf;
    private JTextField nameField;
    private JTextField descField;
    private JComboBox<ClothingType> typeField;
    private JColorChooser colourField;

    // EFFECTS: Creates a wardrobe panel with an assigned wardrobe and frame
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
        removeAll();
        isViewClothing = true;
        currentClothing = c;
        wearClothingButton();
        washClothingButton();
        revalidate();
    }

    // MODIFIES: this
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

    // MODIFIES: currentClothing
    // EFFECTS: Adds new clothing
    private void addClothingButton() {
        JButton button = new JButton("Add");
        button.setActionCommand("add");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.addClothing(new Clothing((ClothingType) typeField.getSelectedItem(), colourField.getColor(),
                        nameField.getText(), descField.getText(), idTracker++));
                wf.updateClothingList();
                addClothingPanel();
            }
        });

        add(button);
    }

    // MODIFIES: currentClothing
    // EFFECTS: Wears clothing
    private void wearClothingButton() {
        JButton button = new JButton("Wear");
        button.setActionCommand("wear");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentClothing.wear();
            }
        });

        add(button);
    }

    // MODIFIES: currentClothing
    // EFFECTS: Washes clothing
    private void washClothingButton() {
        JButton button = new JButton("Wash");
        button.setActionCommand("wash");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentClothing.wash();
            }
        });

        add(button);
    }

    // EFFECTS: initializes the instances used for the wardrobe panel
    private void init() {
        idTracker = 0;
        isViewClothing = true;
    }

    // EFFECTS: Paints the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawWardrobe(g);
        repaint();

    }

    // MODIFIES: this
    // EFFECTS: Draws wardrobe menu
    private void drawWardrobe(Graphics g) {
        if (isViewClothing) {
            drawSingleClothing(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: Draws a single clothing
    private void drawSingleClothing(Graphics g) {
        if (currentClothing != null) {
            g.setColor(currentClothing.getColour());
            g.fillRect(10, 20, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString(currentClothing.getName(), 100, 20);
            g.drawString(currentClothing.getDescription(), 200, 20);
            g.drawString(currentClothing.getClothingType().toString(), 20, 200);
            drawType(g, currentClothing.getClothingType());

            g.drawString(Integer.toString(currentClothing.getTotalTimesWorn()), 60, 200);
            g.drawString(Integer.toString(currentClothing.getTimesWornSinceWash()), 90, 200);
        }

    }

    private void drawType(Graphics g, ClothingType clothingType) {
        BufferedImage myPicture;
        try {
            switch (clothingType) {
                case TOP:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/top.jpg"));
                    break;
                case BOTTOMS:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/bottoms.jpg"));
                    break;
                case JACKET:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/jacket.jpg"));
                    break;
                case SHOES:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/shoes.jpg"));
                    break;
                case HEADWEAR:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/headwear.jpg"));
                    break;
                default:
                    myPicture = ImageIO.read(new File("./src/main/ui/imgs/accessory.jpg"));
                    break;
            }
            g.drawImage(myPicture, 100, 100, null);
        } catch (Exception e) {
            System.err.println("IOException");
        }
    }

    // MODIFIES: this
    // EFFECTS: Clear the panel
    public void clear() {
        removeAll();
        currentClothing = null;
    }

}