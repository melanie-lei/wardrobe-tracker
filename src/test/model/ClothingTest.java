package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Clothing.ClothingType;

public class ClothingTest {

    private Clothing clothing;
    private Clothing overridedClothing;
    private Clothing accessory;

    @BeforeEach
    void runBefore() {
        clothing = new Clothing(ClothingType.TOP, Color.blue, "my t-shirt", "got it at h&m", 0);
        overridedClothing = new Clothing(ClothingType.TOP, Color.blue, "my t-shirt", "got it at h&m", 2, 4, 5, true);
        accessory = new Clothing(ClothingType.ACCESSORY, Color.blue, "my t-shirt", "got it at h&m", 1);
    }

    @Test
    void testContructor() {
        assertFalse(clothing.isFavourite());
        assertEquals(0, clothing.getTimesWornSinceWash());
        assertEquals(0, clothing.getTotalTimesWorn());
        assertEquals(Color.blue, clothing.getColour());
        assertEquals("my t-shirt", clothing.getName());
        assertEquals("got it at h&m", clothing.getDescription());
        assertEquals(0, clothing.getId());
    }

    @Test
    void testOverridedContructor() {
        assertTrue(overridedClothing.isFavourite());
        assertEquals(5, overridedClothing.getTimesWornSinceWash());
        assertEquals(4, overridedClothing.getTotalTimesWorn());
        assertEquals(Color.blue, overridedClothing.getColour());
        assertEquals("my t-shirt", overridedClothing.getName());
        assertEquals("got it at h&m", overridedClothing.getDescription());
        assertEquals(2, overridedClothing.getId());
    }

    @Test
    void testWear() {
        clothing.wear();
        assertEquals(1, clothing.getTotalTimesWorn());
        assertEquals(1, clothing.getTimesWornSinceWash());

        clothing.wear();
        assertEquals(2, clothing.getTotalTimesWorn());
        assertEquals(2, clothing.getTimesWornSinceWash());

        accessory.wear();
        assertEquals(1, accessory.getTotalTimesWorn());
        assertEquals(0, accessory.getTimesWornSinceWash());
    }

    @Test
    void testWash() {
        clothing.wear();
        clothing.wear();
        assertEquals(2, clothing.getTimesWornSinceWash());
        clothing.wash();
        assertEquals(0, clothing.getTimesWornSinceWash());
    }
}
