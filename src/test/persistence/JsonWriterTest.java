package persistence;

import org.junit.jupiter.api.Test;

import model.Clothing;
import model.Clothing.ClothingType;
import model.Outfit;
import model.Wardrobe;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// SOURCE: JsonReaderTest example given for phase 2 instructions
class JsonWriterTest extends JsonTest {
    // NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter
    // is to
    // write data to a file and then use the reader to read it back in and check
    // that we
    // read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Wardrobe wd = new Wardrobe();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Wardrobe wd = new Wardrobe();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWardrobe.json");
            writer.open();
            writer.write(wd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWardrobe.json");
            wd = reader.read();
            assertEquals(0, wd.getClothing().size());
            assertEquals(0, wd.getOutfits().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralWorkroom() {
        try {
            Wardrobe wd = new Wardrobe();

            Outfit outfit = new Outfit();
            Clothing necklace = new Clothing(ClothingType.ACCESSORY, Color.red, "Necklace", "bought it at __", 0);
            Clothing bracelet = new Clothing(ClothingType.ACCESSORY, Color.blue, "Bracelet", "bought it at __", 1);
            Clothing top = new Clothing(ClothingType.TOP, Color.blue, "Top Name", "Description", 2, 4, 5, false);
            Clothing jacket = new Clothing(ClothingType.JACKET, Color.blue, "Name", "Description", 3);
            Clothing bottoms = new Clothing(ClothingType.BOTTOMS, Color.blue, "Name", "Description", 4);
            Clothing shoes = new Clothing(ClothingType.SHOES, Color.blue, "Name", "Description", 5);
            Clothing headwear = new Clothing(ClothingType.HEADWEAR, Color.blue, "Name", "Description", 6);

            wd.addClothing(necklace);
            wd.addClothing(bracelet);
            wd.addClothing(top);
            wd.addClothing(jacket);
            wd.addClothing(bottoms);
            wd.addClothing(shoes);
            wd.addClothing(headwear);

            outfit.setTop(top);
            outfit.setJacket(jacket);
            outfit.setBottoms(bottoms);
            outfit.setShoes(shoes);
            outfit.setHeadwear(headwear);
            outfit.addAccessory(necklace);
            outfit.addAccessory(bracelet);
            outfit.setName("outfit name");
            outfit.setDescription("oufit description");

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWardrobe.json");
            writer.open();
            writer.write(wd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWardrobe.json");
            wd = reader.read();
            assertEquals(7, wd.getClothing().size());
            assertEquals(1, wd.getOutfits().size());
            checkClothing(2, "Top Name", "Description", Color.blue, 4, 5, false, wd.getClothing().get(2));
            List<Integer> accessoryList = new ArrayList<>();
            accessoryList.add(0);
            accessoryList.add(1);
            checkOutfit("outfit name", "outfit description", 2, 3, 4, 5, 6, accessoryList, outfit);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}