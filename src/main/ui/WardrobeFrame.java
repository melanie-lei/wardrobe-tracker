package ui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Clothing;
import model.EventLog;
import model.Wardrobe;

public class WardrobeFrame extends JFrame {

    private Wardrobe wardrobe;
    private WardrobePanel wp;
    private WardrobeSaver wardrobeSaver;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private DefaultListModel<Clothing> clothingL;
    private JList<Clothing> clothingList;
    private GridBagConstraints constraints;

    // EFFECTS: Creates an instance of WardrobeFrame and instantiates displays
    WardrobeFrame() {
        super("Wardrobe");
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new MyWindowListener());
        wardrobe = new Wardrobe();
        wardrobeSaver = new WardrobeSaver();

        wp = new WardrobePanel(wardrobe, this);
        addComponents();

        pack();
        setVisible(true);
    }

    // EFFECTS: Adds the JComponents to the frame
    private void addComponents() {
        constraints.fill = GridBagConstraints.BOTH;
        chooseClothingButton();
        sortClothingAlphabeticalButton();
        sortClothingWornButton();
        newClothingButton();
        loadFileButton();
        saveFileButton();
        clothingList();

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        add(wp, constraints);
    }

    // MODIFIES: wardrobe.json
    // EFFECTS: Creates and adds a save file button
    private void saveFileButton() {
        JButton button = new JButton("Save File");
        button.setActionCommand("save");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobeSaver.saveWardrobe(wardrobe);
            }
        });
        constraints.ipadx = 60;
        constraints.gridx = 4;
        constraints.gridy = 0;
        add(button, constraints);
    }

    // MODIFIES: wardrobe
    // EFFECTS: Creates and adds a load file button
    private void loadFileButton() {
        JButton button = new JButton("Load File");
        button.setActionCommand("load");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe = wardrobeSaver.loadWardrobe();
                wp.updateWardrobe(wardrobe);
                updateClothingList();
                clothingList.clearSelection();
                wp.clear();
            }
        });
        constraints.ipadx = 60;
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(button, constraints);
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
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(button, constraints);
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
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(button, constraints);
    }

    // MODIFIES: wardrobe, this
    // EFFECTS: Sorts clothing by times worn
    private void sortClothingWornButton() {
        JButton button = new JButton("Sort Worn Most");
        button.setActionCommand("sort");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wardrobe.sortClothing(wardrobe.getClothing(), "totalTimesWornDescending");
                updateClothingList();
            }
        });
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(button, constraints);
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
        constraints.ipadx = 700;
        constraints.gridx = 3;
        constraints.gridy = 0;
        add(button, constraints);
    }

    // MODIFIES: this
    // EFFECTS: adds list of clothing to a JList and puts it on the frame
    private void clothingList() {
        clothingL = new DefaultListModel<>();
        updateClothingList();
        clothingList = new JList<>(clothingL);
        clothingList.setVisibleRowCount(10);
        clothingList.setCellRenderer(new ClothingListCellRenderer());
        constraints.ipadx = 20;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        add(new JScrollPane(clothingList), constraints);

    }

    // MODIFIES: this
    // EFFECTS: Updates the clothing list
    public void updateClothingList() {
        clothingL.clear();
        for (Clothing c : wardrobe.getClothing()) {
            clothingL.addElement(c);
        }
    }

    // EFFECTS: Window listener for when the window closes
    private class MyWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            EventLog eventLog = EventLog.getInstance();
            for (model.Event event : eventLog) {
                System.out.println(event.toString());
            }
        }
    }


}
