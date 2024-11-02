package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

public class WardrobeSaver {

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_PATH = "./data/wardrobe.json";

    WardrobeSaver() {
        
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
    }

     // EFFECTS: writes the wardrobe into JSON data and saves it
    public void saveWardrobe(Wardrobe wardrobe) {
        try {
            jsonWriter.open();
            jsonWriter.write(wardrobe);
            jsonWriter.close();
            System.out.println("Saved wardrobe to " + JSON_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PATH);
        }
    }

    // EFFECTS: reads the saved JSON data and loads it
    public void loadWardrobe(Wardrobe wardrobe) {
        try {
            wardrobe = jsonReader.read();
            System.out.println("Loaded wardrobe from " + JSON_PATH);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
    }
}
