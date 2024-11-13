package ui;


import java.util.List;
import java.util.Vector;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.Clothing;
import model.Clothing.ClothingType;
import model.Outfit;
import model.Wardrobe;

public class WardrobeFrame extends JFrame {

    private Wardrobe wardrobe;
    private WardrobePanel wp;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private DefaultListModel<Clothing> clothingL;
    private JList<Clothing> clothingList;
    private GridBagConstraints c;

    // EFFECTS: Creates an instance of WardrobeFrame and instantiates displays
    WardrobeFrame() {
        super("Wardrobe");
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wardrobe = new Wardrobe();

        wp = new WardrobePanel(wardrobe, this);
        c.fill = GridBagConstraints.BOTH;
        chooseClothingButton();
        sortClothingAlphabeticalButton();
        sortClothingWornButton();
        newClothingButton();
        clothingList();

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        add(wp, c);
        
        pack();
        setVisible(true);
    }

    // MODIFIES: wp
    // EFFECTS: Chooses the clothing selected on the list
    private void chooseClothingButton() {
        JButton button = new JButton("View");
        button.setActionCommand("view");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wp.changeClothing(clothingList.getSelectedValue());
			}
        });
        c.gridx = 0;
        c.gridy = 0;
        add(button, c);
    }

    // MODIFIES: wardrobe, this
    // EFFECTS: Sorts clothing alphabetically
    private void sortClothingAlphabeticalButton() {
        JButton button = new JButton("Sort A -> Z");
        button.setActionCommand("sort");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.sortClothing(wardrobe.getClothing(), "alphabetical");
                updateClothingList();
			}
        });
        c.gridx = 1;
        c.gridy = 0;
        add(button, c);
    }

    // MODIFIES: wardrobe, this
    // EFFECTS: Sorts clothing by times worn
    private void sortClothingWornButton() {
        JButton button = new JButton("Sort Worn");
        button.setActionCommand("sort");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.sortClothing(wardrobe.getClothing(), "totalTimesWornAscending");
                updateClothingList();
			}
        });
        c.gridx = 2;
        c.gridy = 0;
        add(button, c);
    }

    // MODIFIES: this
    // EFFECTS: Turns display panel into adding new clothing panel
    private void newClothingButton() {
        JButton button = new JButton("New");
        button.setActionCommand("new");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clothingList.clearSelection();
                wp.addClothingPanel();
			}
        });
        c.gridx = 3;
        c.gridy = 0;
        add(button, c);
    }

    // MODIFIES: this
    // EFFECTS: adds list of clothing to a JList and puts it on the frame
    private void clothingList() {
        clothingL = new DefaultListModel<>();
        updateClothingList();
        clothingList = new JList<>(clothingL);
        clothingList.setVisibleRowCount(10);
        clothingList.setCellRenderer(new ClothingListCellRenderer());
        c.ipadx = 20;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        add(new JScrollPane(clothingList), c);
        
    }

    // MODIFIES: this
    // EFFECTS: Updates the clothing list
    public void updateClothingList() {
        clothingL.clear();
        for (Clothing c : wardrobe.getClothing()) {
            clothingL.addElement(c);
        }
    }

}
