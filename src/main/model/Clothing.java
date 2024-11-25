package model;

import java.awt.Color;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// A Clothing item that keeps track of its own properties:
// type, colour, totalTimesWorn, timesWornSinceWash, isFavourite, name, description
public class Clothing implements Writable {
    public enum ClothingType {
        TOP,
        JACKET,
        BOTTOMS,
        SHOES,
        HEADWEAR,
        ACCESSORY
    }

    private ClothingType type;
    private Color colour;
    private int totalTimesWorn;
    private int timesWornSinceWash;
    private boolean isFavourite;
    private String name;
    private String description;
    private int id;

    // EFFECTS: creates a Clothing item with:
    // a specified ClothingType, colour, totalTimesWorn = 0,
    // timesWornSinceWash = 0, isFavourite = false, name, description
    public Clothing(ClothingType t, Color c, String n, String d, int id) {
        this.type = t;
        this.colour = c;
        this.name = n;
        this.description = d;
        this.totalTimesWorn = 0;
        this.timesWornSinceWash = 0;
        this.isFavourite = false;
        this.id = id;
    }

    // EFFECTS: creates a Clothing item with:
    // a specified ClothingType, colour, totalTimesWorn,
    // timesWornSinceWash, isFavourite, name, description
    public Clothing(ClothingType t, Color c, String n, String d, int id, int tt, int tw, boolean f) {
        this.type = t;
        this.colour = c;
        this.name = n;
        this.description = d;
        this.totalTimesWorn = tt;
        this.timesWornSinceWash = tw;
        this.isFavourite = f;
        this.id = id;
    }

    // MODIFIES: this
    // EFFECTS: increments totalTimesWorn and timesWornSinceWash but not for
    // accessories
    public Clothing wear() {
        this.totalTimesWorn++;
        if (this.type != ClothingType.ACCESSORY) {
            this.timesWornSinceWash++;
        }
        return this;
    }

    // MODIFIES: this
    // EFFECTS: indicates this clothing item as washed and resets times worn since
    // last wash
    public void wash() {
        this.timesWornSinceWash = 0;
    }

    public void setFavourite(boolean bool) {
        this.isFavourite = bool;
    }

    public boolean isFavourite() {
        return this.isFavourite;
    }

    public int getTimesWornSinceWash() {
        return this.timesWornSinceWash;
    }

    public int getTotalTimesWorn() {
        return this.totalTimesWorn;
    }

    public Color getColour() {
        return this.colour;
    }

    public ClothingType getClothingType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("description", this.description);
        json.put("type", typeToString());
        json.put("colour", this.colour.getRGB());
        json.put("totalTimesWorn", this.totalTimesWorn);
        json.put("timesWornSinceWash", this.timesWornSinceWash);
        json.put("isFavourite", this.isFavourite);
        return json;
    }

    // EFFECTS: returns this clothing type as string
    private String typeToString() {
        switch (type) {
            case TOP:
                return "TOP";
            case JACKET:
                return "JACKET";
            case BOTTOMS:
                return "BOTTOMS";
            case SHOES:
                return "SHOES";
            case HEADWEAR:
                return "HEADWEAR";
            default:
                return "ACCESSORY";
        }
    }

}
