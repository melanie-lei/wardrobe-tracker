package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

// a class to save and load json
public class WardrobeSaver {

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_PATH = "C:\\Users\\lei31\\CPSC 210\\Final Project\\project-k3k1a\\data\\wardrobe.json";

    // EFFECTS: creates an instance of WardrobeSaver with jsonWriter and jsonReader
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
    public Wardrobe loadWardrobe() {
        try {
            System.out.println("Loaded wardrobe from " + JSON_PATH);
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
        return null;
    }
}
