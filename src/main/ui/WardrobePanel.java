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
    private GridBagConstraints constraints;

    // EFFECTS: Creates a wardrobe panel with an assigned wardrobe and frame
    WardrobePanel(Wardrobe wd, WardrobeFrame wf) {
        super();
        init();
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
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
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        addNameField();
        addDescriptionField();
        addTypeField();
        addColourField();
        addClothingButton();
        revalidate();
    }

    // EFFECTS: Changes layout constraints and adds a colourField
    private void addColourField() {
        colourField = new JColorChooser();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        add(colourField, constraints);
    }

    // EFFECTS: Changes layout constraints and adds a typeField
    private void addTypeField() {
        typeField = new JComboBox<>();
        typeField.addItem(ClothingType.TOP);
        typeField.addItem(ClothingType.BOTTOMS);
        typeField.addItem(ClothingType.JACKET);
        typeField.addItem(ClothingType.SHOES);
        typeField.addItem(ClothingType.HEADWEAR);
        typeField.addItem(ClothingType.ACCESSORY);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(typeField, constraints);
    }

    // EFFECTS: Changes layout constraints and adds a descField
    private void addDescriptionField() {
        constraints.gridx = 1;
        constraints.gridy = 0;
        descField = new JTextField("Description");
        add(descField, constraints);
    }

    // EFFECTS: Changes layout constraints and adds a nameField
    private void addNameField() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 80;
        nameField = new JTextField("Name");
        add(nameField, constraints);
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
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(button, constraints);
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

    // EFFECTS: Initializes the instances used for the wardrobe panel
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
            g.fillRect(400, 20, 200, 30);
            g.setColor(Color.BLACK);
            g.drawString("Name: " + currentClothing.getName(), 20, 20);
            g.drawString("Description: " + currentClothing.getDescription(), 20, 40);
            drawType(g, currentClothing.getClothingType());
            g.drawString("Total Times Worn: " + Integer.toString(currentClothing.getTotalTimesWorn()), 20, 80);
            g.drawString("Times Worn Since Wash: " + Integer.toString(currentClothing.getTimesWornSinceWash()), 20,
                    100);
        }

    }

    // EFFECTS: Draws the image based on clothing type
    @SuppressWarnings("methodlength")
    private void drawType(Graphics g, ClothingType clothingType) {
        BufferedImage myPicture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
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
            g.drawImage(myPicture, 400, 80, 200, 180, null);
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

    public void updateWardrobe(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }

}