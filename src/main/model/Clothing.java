package model;
import java.awt.Color;

// A Clothing item that keeps track of its own properties:
// type, colour, totalTimesWorn, timesWornSinceWash, isFavourite, name, description
public class Clothing {
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

    // EFFECTS: creates a Clothing item with:
    // a specified ClothingType, colour, totalTimesWorn = 0, 
    // timesWornSinceWash = 0, isFavourite = false, name, description
    public Clothing(ClothingType t, Color c, String n, String d){
        this.type = t;
        this.colour = c;
        this.name = n;
        this.description = d;
        this.totalTimesWorn = 0;
        this.timesWornSinceWash = 0;
        this.isFavourite = false;
    }

    // MODIFIES: this
    // EFFECTS: increments totalTimesWorn and timesWornSinceWash
    public void wear(){
        this.totalTimesWorn++;
        this.timesWornSinceWash++;
    }

    // MODIFIES: this
    // EFFECTS: indicates this clothing item as washed and resets times worn since last wash
    public void wash(){
        this.timesWornSinceWash = 0;
    }

    public void setFavourite(boolean bool){
        this.isFavourite = bool;
    }

    public boolean isFavourite(){
        return this.isFavourite;
    }

    public int getTimesWornSinceWash(){
        return this.timesWornSinceWash;
    }

    public int getTotalTimesWorn(){
        return this.totalTimesWorn;
    }

    public ClothingType getClothingType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

}
