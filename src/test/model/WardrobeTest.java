package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Clothing.ClothingType;
import java.awt.Color;

public class WardrobeTest {
    Wardrobe wardrobe;
    Outfit outfit;
    Clothing clothing1;
    Clothing clothing2;

    @BeforeEach
    void runBefore(){
        wardrobe = new Wardrobe();
    }

    @Test
    void testConstructor(){
        assertEquals(0, wardrobe.getClothing().size());
        assertEquals(0, wardrobe.getOutfits().size());
    }

    @Test
    void testAddOutfit(){
        outfit = new Outfit();
        outfit.setName("my outfit");
        wardrobe.addOutfit(outfit);
        assertEquals("my outfit", wardrobe.getOutfits().get(0).getName());
        assertEquals(1, wardrobe.getOutfits().size());
    }

    @Test
    void testAddClothing(){
        clothing1 = new Clothing(ClothingType.TOP, Color.blue, "blue shirt", "bought at h&m");
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(0));
        assertEquals(1, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getTops().get(0));
        assertEquals(1, wardrobe.getTops().size());
        
    }

    @Test
    void testSortClothing(){
        clothing1 = new Clothing(ClothingType.TOP, Color.blue, "blue shirt", "bought at h&m");
        clothing2 = new Clothing(ClothingType.TOP, Color.green, "green shirt", "bought at zara");
        wardrobe.addClothing(clothing2);
        wardrobe.addClothing(clothing1);
        wardrobe.sortClothing(wardrobe.getClothing(), "alphabetical");
        assertEquals("blue shirt", wardrobe.getClothing().get(0).getName());
        
        clothing2.wear();
        wardrobe.sortClothing(wardrobe.getClothing(), "totalTimesWorn");
        assertEquals("green shirt", wardrobe.getClothing().get(0).getName());
    }

}
