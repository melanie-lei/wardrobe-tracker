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
    private List<Clothing> displayedClothing;
    private List<Outfit> displayedOutfits;
    private JList<Clothing> clothingList;

    // EFFECTS: Creates an instance of WardrobeFrame and instantiates displays
    WardrobeFrame() {
        super("Wardrobe");
        setLayout(new GridLayout(2, 3));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wardrobe = new Wardrobe();
        wardrobe.addClothing(new Clothing(ClothingType.TOP, Color.blue, "my shirt", "my description", 0));
        wardrobe.addClothing(new Clothing(ClothingType.BOTTOMS, Color.red, "my pants", "my pants description", 1));
        Outfit o = new Outfit();
        o.setName("name of outfit");
        wardrobe.addOutfit(o);
        wp = new WardrobePanel(wardrobe);
        clothingList();
        outfitList();
        add(wp);
        chooseClothingButton();
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
        add(button);
    }

    // EFFECTS: adds list of clothing to a JList and puts it on the frame
    private void clothingList() {
        displayedClothing = wardrobe.getClothing();
        clothingList = new JList<>(new Vector<Clothing>(displayedClothing));
        clothingList.setVisibleRowCount(10);
        clothingList.setCellRenderer(new ClothingListCellRenderer());
        add(new JScrollPane(clothingList));
        
    }

    // EFFECTS: adds list of outfit to a JList and puts it on the frame
    private void outfitList() {
        displayedOutfits = wardrobe.getOutfits();
        JList<Outfit> list = new JList<>(new Vector<Outfit>(displayedOutfits));
        list.setVisibleRowCount(10);
        list.setCellRenderer(new OutfitListCellRenderer());
        add(new JScrollPane(list));
        
    }

}
