package persistence;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Wardrobe;
import model.Clothing;
import model.Clothing.ClothingType;
import model.Outfit;

// SOURCE: JsonReader example given for phase 2 instructions
// a reader that reads JSON representation the wardrobe from file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads wardrobe from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Wardrobe read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWardobe(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses wardrobe from JSON object and returns it
    private Wardrobe parseWardobe(JSONObject jsonObject) {
        Wardrobe wd = new Wardrobe();
        addClothing(wd, jsonObject);
        addOutfits(wd, jsonObject);
        return wd;
    }

    // MODIFIES: w
    // EFFECTS: parses clothing from JSON object and adds them to wardrobe
    private void addClothing(Wardrobe wd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clothing");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addSingleClothing(wd, nextClothing);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses single clothing from JSON object and adds it to wardrobe
    private void addSingleClothing(Wardrobe wd, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        String description = jsonObject.getString("description");
        ClothingType type = toClothingType(jsonObject.getString("type"));
        Color colour = new Color(jsonObject.getInt("colour"));
        int totalTimesWorn = jsonObject.getInt("totalTimesWorn");
        int timesWornSinceWash = jsonObject.getInt("timesWornSinceWash");
        boolean isFavourite = jsonObject.getBoolean("isFavourite");
        Clothing clothing = new Clothing(type, colour, name, description, id,
                totalTimesWorn, timesWornSinceWash, isFavourite);
        wd.addClothing(clothing);
    }

    private ClothingType toClothingType(String s) {
        switch (s) {
            case "TOP":
                return ClothingType.TOP;
            case "JACKET":
                return ClothingType.JACKET;
            case "BOTTOMS":
                return ClothingType.BOTTOMS;
            case "SHOES":
                return ClothingType.SHOES;
            case "HEADWEAR":
                return ClothingType.HEADWEAR;
            default:
                return ClothingType.ACCESSORY;
        }
    }

    // MODIFIES: w
    // EFFECTS: parses outfits from JSON object and adds them to wardrobe
    private void addOutfits(Wardrobe wd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("outfits");
        for (Object json : jsonArray) {
            JSONObject nextOutfit = (JSONObject) json;
            addSingleOutfit(wd, nextOutfit);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses single outfit from JSON object and adds it to wardrobe
    private void addSingleOutfit(Wardrobe wd, JSONObject jsonObject) {
        Outfit outfit = new Outfit();
        outfit.setName(jsonObject.getString("name"));
        outfit.setDescription(jsonObject.getString("description"));
        outfit.setTop(wd.clothingLookup(jsonObject.getInt("top")));
        outfit.setJacket(wd.clothingLookup(jsonObject.getInt("jacket")));
        outfit.setBottoms(wd.clothingLookup(jsonObject.getInt("bottoms")));
        outfit.setShoes(wd.clothingLookup(jsonObject.getInt("shoes")));
        outfit.setHeadwear(wd.clothingLookup(jsonObject.getInt("headwear")));
        for (Object json : jsonObject.getJSONArray("accessories")) {
            outfit.addAccessory(wd.clothingLookup((int) json));
        }
        wd.addOutfit(outfit);
    }
}
