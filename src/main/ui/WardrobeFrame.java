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
        wardrobe.addClothing(new Clothing(ClothingType.TOP, Color.blue, "my shirt", "my description", 0));
        wardrobe.addClothing(new Clothing(ClothingType.BOTTOMS, Color.red, "amy pants", "my pants description", 1));

        wp = new WardrobePanel(wardrobe);
        c.fill = GridBagConstraints.BOTH;
        chooseClothingButton();
        sortClothingAlphabeticalButton();
        sortClothingWornButton();
        newClothingButton();
        addClothingButton();

        clothingList();
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 2;
        add(wp, c);
        
        
        pack();
        setVisible(true);
    }

    private void chooseClothingButton() {
        JButton button = new JButton("Choose");
        button.setActionCommand("choose");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wp.changeClothing(clothingList.getSelectedValue());
                System.out.println(clothingList.getSelectedValue().getName());
			}
        });
        c.gridx = 0;
        c.gridy = 0;
        add(button, c);
    }

    private void sortClothingAlphabeticalButton() {
        JButton button = new JButton("Sort A -> Z");
        button.setActionCommand("choose");
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

    private void sortClothingWornButton() {
        JButton button = new JButton("Sort Worn");
        button.setActionCommand("choose");
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

    private void newClothingButton() {
        JButton button = new JButton("New");
        button.setActionCommand("choose");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wp.addClothingPanel();
			}
        });
        c.gridx = 3;
        c.gridy = 0;
        add(button, c);
    }

    private void addClothingButton() {
        JButton button = new JButton("Add");
        button.setActionCommand("choose");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.addClothing(new Clothing(ClothingType.BOTTOMS, Color.red, "my pants", "my pants description", 2));
                updateClothingList();
			}
        });
        c.gridx = 4;
        c.gridy = 0;
        add(button, c);
    }

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

    private void updateClothingList() {
        clothingL.clear();
        for (Clothing c : wardrobe.getClothing()) {
            clothingL.addElement(c);
        }
    }

}
