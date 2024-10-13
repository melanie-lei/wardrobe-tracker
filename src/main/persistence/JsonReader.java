package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Wardrobe;

// SOURCE: JsonReader example given for phase 2 instructions
// a reader that reads JSON representation the wardrobe from file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads wardrobe from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Wardrobe read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses wardrobe from JSON object and returns it
    private Wardrobe parseWardobe(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: w
    // EFFECTS: parses clothing from JSON object and adds them to wardrobe
    private void addClothing(Wardrobe w, JSONObject jsonObject) {

    }

    // MODIFIES: w
    // EFFECTS: parses single clothing from JSON object and adds it to wardrobe
    private void addSingleClothing(Wardrobe w, JSONObject jsonObject) {

    }

    // MODIFIES: w
    // EFFECTS: parses outfits from JSON object and adds them to wardrobe
    private void addOutfits(Wardrobe w, JSONObject jsonObject) {

    }

    // MODIFIES: w
    // EFFECTS: parses single outfit from JSON object and adds it to wardrobe
    private void addSingleOutfit(Wardrobe w, JSONObject jsonObject) {

    }
}
