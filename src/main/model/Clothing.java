package model;
import java.awt.Color;

// A Clothing item that keeps track of its own properties:
// type, colour, totalTimesWorn, timesWornSinceWash, isFavourite
public class Clothing {
    public enum ClothingType {
        TOP,
        BOTTOM,
        SHOES,
        HEADWEAR,
        ACCESSORY
    }

    // EFFECTS: creates a Clothing item with:
    // a specified ClothingType, colour, totalTimesWorn = 0, timesWornSinceWash = 0, isFavourite = false
    public Clothing(ClothingType t, Color c){
    }

    // MODIFIES: this
    // EFFECTS: increments totalTimesWorn and timeWornSinceWash
    public void wear(){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: indicates this clothing item as washed and resets times worn since last wash
    public void wash(){
        //stub
    }

    public void setFavourite(){
        //stub
    }

    public boolean isFavourite(){
        return false; //stub
    }

    public int getTimesWornSinceWash(){
        return 0; //stub
    }

    public int getTotalTimesWorn(){
        return 0; //stub
    }

    public ClothingType getClothingType(){
        return null; //stub
    }

}
