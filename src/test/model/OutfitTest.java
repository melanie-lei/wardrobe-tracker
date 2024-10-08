package model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Clothing.ClothingType;

public class OutfitTest {

    Outfit outfit;
    Clothing necklace;
    Clothing bracelet;

    @BeforeEach
    void runBefore() {
        outfit = new Outfit();
        necklace = new Clothing(ClothingType.ACCESSORY, Color.red, "Necklace", "bought it at __");
        bracelet = new Clothing(ClothingType.ACCESSORY, Color.blue, "Bracelet", "bought it at __");
    }

    @Test
    void testConstructor() {
        List<Clothing> empty = new ArrayList<Clothing>();
        assertEquals(null, outfit.getTop());
        assertEquals(null, outfit.getJacket());
        assertEquals(null, outfit.getBottoms());
        assertEquals(null, outfit.getShoes());
        assertEquals(null, outfit.getHeadwear());
        assertEquals(empty, outfit.getAccessories());
        assertEquals(null, outfit.getName());
        assertEquals(null, outfit.getDescription());
    }

    @Test
    void testAddAccessory() {
        outfit.addAccessory(necklace);
        assertEquals(necklace, outfit.getAccessories().get(0));
        assertEquals(1, outfit.getAccessories().size());
    }

    @Test
    void testRemoveAccessory() {
        outfit.addAccessory(necklace);
        outfit.addAccessory(bracelet);
        outfit.removeAccessory(necklace);
        assertEquals(bracelet, outfit.getAccessories().get(0));
        assertEquals(1, outfit.getAccessories().size());
    }

    @Test
    void testSetters() {
        Clothing top = new Clothing(ClothingType.TOP, Color.blue, "Name", "Description");
        Clothing jacket = new Clothing(ClothingType.JACKET, Color.blue, "Name", "Description");
        Clothing bottoms = new Clothing(ClothingType.BOTTOMS, Color.blue, "Name", "Description");
        Clothing shoes = new Clothing(ClothingType.SHOES, Color.blue, "Name", "Description");
        Clothing headwear = new Clothing(ClothingType.HEADWEAR, Color.blue, "Name", "Description");
        outfit.setTop(top);
        outfit.setJacket(jacket);
        outfit.setBottoms(bottoms);
        outfit.setShoes(shoes);
        outfit.setHeadwear(headwear);
        assertEquals(top, outfit.getTop());
        assertEquals(jacket, outfit.getJacket());
        assertEquals(bottoms, outfit.getBottoms());
        assertEquals(shoes, outfit.getShoes());
        assertEquals(headwear, outfit.getHeadwear());
    }
}
