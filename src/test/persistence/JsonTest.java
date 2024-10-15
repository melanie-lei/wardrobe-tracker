package persistence;

import model.Clothing;
import model.Outfit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.List;

public class JsonTest {

    protected void checkClothing(int id, String name, String description, Color colour, int totalTimesWorn,
            int timesWornSinceWash, Clothing clothing) {
        assertEquals(id, clothing.getId());
        assertEquals(name, clothing.getName());
        assertEquals(description, clothing.getDescription());
        assertEquals(colour, clothing.getColour());
        assertEquals(totalTimesWorn, clothing.getTotalTimesWorn());
        assertEquals(timesWornSinceWash, clothing.getTimesWornSinceWash());

    }

    protected void checkOutfit(String name, String description, int top, int jacket, int bottoms, int shoes,
            int headwear, List<Integer> accessories, Outfit outfit) {
        assertEquals(name, outfit.getName());
        assertEquals(description, outfit.getDescription());
        assertEquals(top, outfit.getTop().getId());
        assertEquals(jacket, outfit.getJacket().getId());
        assertEquals(bottoms, outfit.getBottoms().getId());
        assertEquals(shoes, outfit.getShoes().getId());
        assertEquals(headwear, outfit.getHeadwear().getId());
        int c = 0;
        for(Integer i : accessories) {
            assertEquals(i, outfit.getAccessories().get(c++).getId());
        }
    }

}