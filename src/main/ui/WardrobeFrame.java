package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

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

    // EFFECTS: Creates an instance of WardrobeFrame and instantiates displays
    WardrobeFrame() {
        super("Wardrobe");
        setLayout(new GridLayout(1, 3));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wardrobe = new Wardrobe();
        wardrobe.addClothing(new Clothing(ClothingType.TOP, Color.blue, "my shirt", "my description", 0));
        wardrobe.addClothing(new Clothing(ClothingType.BOTTOMS, Color.blue, "my pants", "my pants description", 1));
        Outfit o = new Outfit();
        o.setName("name of outfit");
        wardrobe.addOutfit(o);
        wp = new WardrobePanel(wardrobe);
        clothingList();
        outfitList();
        add(wp);
        pack();
        setVisible(true);
    }

    // EFFECTS: adds list of clothing to a JList and puts it on the frame
    private void clothingList() {
        displayedClothing = wardrobe.getClothing();
        JList<Clothing> list = new JList<>(new Vector<Clothing>(displayedClothing));
        list.setVisibleRowCount(10);
        list.setCellRenderer(new ClothingListCellRenderer());
        add(new JScrollPane(list));
        
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
