package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.Clothing;
import model.Clothing.ClothingType;
import model.Wardrobe;

public class WardrobeFrame extends JFrame {

    private Wardrobe wardrobe;
    private WardrobePanel wp;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private List<Clothing> displayedClothing;

    // EFFECTS: Creates an instance of WardrobeFrame and instantiates displays
    WardrobeFrame() {
        super("Wardrobe");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wardrobe = new Wardrobe();
        wardrobe.addClothing(new Clothing(ClothingType.TOP, Color.blue, "my shirt", "my description", 0));
        wardrobe.addClothing(new Clothing(ClothingType.BOTTOMS, Color.blue, "my pants", "my pants description", 1));
        wp = new WardrobePanel(wardrobe);
        add(wp);
        clothingList();
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

}
