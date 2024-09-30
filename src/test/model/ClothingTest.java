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
    
    @BeforeEach
    void runBefore() {
        clothing = new Clothing(ClothingType.TOP, Color.blue, "my t-shirt", "got it at h&m");
    }

    @Test
    void testContructor() {
        assertFalse(clothing.isFavourite());
        assertEquals(0, clothing.getTimesWornSinceWash());
        assertEquals(0, clothing.getTotalTimesWorn());
    }

    @Test
    void testWear() {
        clothing.wear();
        assertEquals(1, clothing.getTotalTimesWorn());
        assertEquals(1, clothing.getTimesWornSinceWash());
        
        clothing.wear();
        assertEquals(2, clothing.getTotalTimesWorn());
        assertEquals(2, clothing.getTimesWornSinceWash());
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
