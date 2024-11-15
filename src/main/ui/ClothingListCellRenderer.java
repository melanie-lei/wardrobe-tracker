package ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Clothing;

// Child of DefaultListCellRenderer, used to display list of Clothing
public class ClothingListCellRenderer extends DefaultListCellRenderer {

    // EFFECTS: overrides method and displays name of Clothing
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (renderer instanceof JLabel && value instanceof Clothing) {
            ((JLabel) renderer).setText(((Clothing) value).getName());
        }
        return renderer;
    }
}
