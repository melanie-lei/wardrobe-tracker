package persistence;

import model.Clothing;
import model.Outfit;
import model.Wardrobe;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// SOURCE: JsonReaderTest example given for phase 2 instructions
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Wardrobe w = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWardrobe.json");
        try {
            Wardrobe w = reader.read();
            assertEquals(0, w.getOutfits().size());
            assertEquals(0, w.getClothing().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testReaderGeneralWardrobe() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWardrobe.json");
        try {
            Wardrobe w = reader.read();
            List<Outfit> outfits = w.getOutfits();
            assertEquals(1, outfits.size());
            List<Integer> accessoryList = new ArrayList<Integer>();
            accessoryList.add(5);
            checkOutfit("my outfit", "this is a description", 0, 2, 1, 3, 4, accessoryList, outfits.get(0));
            List<Clothing> clothing = w.getClothing();
            assertEquals(6, clothing.size());
            checkClothing(0, "blue shirt", "this is my shirt",
                    new Color(Integer.parseInt("-16776961")), 4, 2, true, clothing.get(0));
            checkClothing(1, "red pants", "these are my pants",
                    new Color(Integer.parseInt("-65536")), 6, 1, false, clothing.get(1));
            checkClothing(2, "red jacket", "my jacket",
                    new Color(Integer.parseInt("-65536")), 6, 2, false, clothing.get(2));
            checkClothing(3, "red shoes", "these are my shoes",
                    new Color(Integer.parseInt("-65536")), 5, 1, false, clothing.get(3));
            checkClothing(4, "red hat", "this is my hat",
                    new Color(Integer.parseInt("-65536")), 3, 1, false, clothing.get(4));
            checkClothing(5, "necklace", "accessory",
                    new Color(Integer.parseInt("-65536")), 6, 4, false, clothing.get(5));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}