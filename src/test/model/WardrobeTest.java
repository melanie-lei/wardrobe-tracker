package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Clothing.ClothingType;
import java.awt.Color;

// console based UI
public class WardrobeTest {
    Wardrobe wardrobe;
    Outfit outfit;
    Clothing clothing1;
    Clothing clothing2;

    @BeforeEach
    void runBefore() {
        wardrobe = new Wardrobe();
    }

    @Test
    void testConstructor() {
        assertEquals(0, wardrobe.getClothing().size());
        assertEquals(0, wardrobe.getOutfits().size());
    }

    @Test
    void testAddOutfit() {
        outfit = new Outfit();
        outfit.setName("my outfit");
        wardrobe.addOutfit(outfit);
        assertEquals("my outfit", wardrobe.getOutfits().get(0).getName());
        assertEquals(1, wardrobe.getOutfits().size());
    }

    @SuppressWarnings("methodlength")
    @Test
    void testAddClothing() {
        clothing1 = new Clothing(ClothingType.TOP, Color.blue, "blue shirt", "bought at h&m", 0);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(0));
        assertEquals(1, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getTops().get(0));
        assertEquals(1, wardrobe.getTops().size());

        clothing1 = new Clothing(ClothingType.JACKET, Color.red, "Red jacket", "note", 1);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(1));
        assertEquals(2, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getJackets().get(0));
        assertEquals(1, wardrobe.getJackets().size());

        clothing1 = new Clothing(ClothingType.BOTTOMS, Color.red, "Red jacket", "note", 2);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(2));
        assertEquals(3, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getBottoms().get(0));
        assertEquals(1, wardrobe.getBottoms().size());

        clothing1 = new Clothing(ClothingType.SHOES, Color.red, "Red jacket", "note", 3);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(3));
        assertEquals(4, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getShoes().get(0));
        assertEquals(1, wardrobe.getShoes().size());

        clothing1 = new Clothing(ClothingType.HEADWEAR, Color.red, "Red jacket", "note", 4);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(4));
        assertEquals(5, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getHeadwear().get(0));
        assertEquals(1, wardrobe.getHeadwear().size());

        clothing1 = new Clothing(ClothingType.ACCESSORY, Color.red, "Red jacket", "note", 5);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.getClothing().get(5));
        assertEquals(6, wardrobe.getClothing().size());
        assertEquals(clothing1, wardrobe.getAccessories().get(0));
        assertEquals(1, wardrobe.getAccessories().size());

    }

    @Test
    void testSortClothing() {
        clothing1 = new Clothing(ClothingType.TOP, Color.blue, "blue shirt", "bought at h&m", 0);
        clothing2 = new Clothing(ClothingType.TOP, Color.green, "green shirt", "bought at zara", 1);
        wardrobe.addClothing(clothing2);
        wardrobe.addClothing(clothing1);
        wardrobe.sortClothing(wardrobe.getClothing(), "alphabetical");
        assertEquals("blue shirt", wardrobe.getClothing().get(0).getName());

        clothing1.wear();
        wardrobe.sortClothing(wardrobe.getClothing(), "totalTimesWornAscending");
        assertEquals("green shirt", wardrobe.getClothing().get(0).getName());

        wardrobe.sortClothing(wardrobe.getClothing(), "totalTimesWornDescending");
        assertEquals("blue shirt", wardrobe.getClothing().get(0).getName());

        clothing2.setFavourite(true);
        wardrobe.sortClothing(wardrobe.getClothing(), "isFavourite");
        assertEquals("green shirt", wardrobe.getClothing().get(0).getName());

        wardrobe.sortClothing(wardrobe.getClothing(), "isFavourite");
        assertEquals("green shirt", wardrobe.getClothing().get(0).getName());

        clothing2.setFavourite(false);
        wardrobe.sortClothing(wardrobe.getClothing(), "isFavourite");
        assertEquals("green shirt", wardrobe.getClothing().get(0).getName());

        clothing1.wear();
        wardrobe.sortClothing(wardrobe.getClothing(), "timesWornSinceWash");
        assertEquals("blue shirt", wardrobe.getClothing().get(0).getName());
    }

    @Test
    void testClothingLookup() {
        clothing1 = new Clothing(ClothingType.TOP, Color.blue, "blue shirt", "bought at h&m", 0);
        wardrobe.addClothing(clothing1);
        assertEquals(clothing1, wardrobe.clothingLookup(0));
        assertNull(wardrobe.clothingLookup(23432));
    }

}
