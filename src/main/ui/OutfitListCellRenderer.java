package ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Outfit;

// Child of DefaultListCellRenderer, used to display list of Outfit
public class OutfitListCellRenderer extends DefaultListCellRenderer {
    
    // EFFECTS: overrides method and displays name of Outfit
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (renderer instanceof JLabel && value instanceof Outfit) {
            ((JLabel) renderer).setText(((Outfit) value).getName());
        }
        return renderer;
    }
}
