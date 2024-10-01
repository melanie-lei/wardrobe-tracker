package model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Clothing.ClothingType;

public class OutfitTest {

    Outfit outfit;
    Clothing necklace;
    Clothing bracelet;
    
    @BeforeEach
    void runBefore(){
        outfit = new Outfit();
        necklace = new Clothing(ClothingType.ACCESSORY, Color.red, "Necklace", "bought it at __");
        bracelet = new Clothing(ClothingType.ACCESSORY, Color.blue, "Bracelet", "bought it at __");
    }

    @Test
    void testConstructor(){
        assertEquals(null, outfit.getTop());
        assertEquals(null, outfit.getJacket());
        assertEquals(null, outfit.getBottoms());
        assertEquals(null, outfit.getShoes());
        assertEquals(null, outfit.getHeadwear());
        assertEquals(null, outfit.getAccessories());
    }

    @Test
    void testAddAccessory(){
        outfit.addAccessory(necklace);
        assertEquals(necklace, outfit.getAccessories().get(0));
        assertEquals(1, outfit.getAccessories().size());
    }

    @Test
    void testRemoveAccessory(){
        outfit.addAccessory(necklace);
        outfit.addAccessory(bracelet);
        outfit.removeAccessory(necklace);
        assertEquals(bracelet, outfit.getAccessories().get(0));
        assertEquals(1, outfit.getAccessories().size());
    }
}
