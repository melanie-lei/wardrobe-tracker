package model;

import java.util.List;

// A wardrobe which consists of Outfits and Clothing items organized by type
public class Wardrobe {
    
    // EFFECTS: Creates a Wardrobe with no outfits or clothing items
    public Wardrobe(){
        //stub
    }

    // REQUIRES: category can only be:
    // "totalTimesWorn", "timesWornSinceWash", "isFavourite", "alphabetical"
    // MODIFIES: list
    // EFFECTS: sorts the given list based on the given category
    public void sortClothing(List<Clothing> list, String category){
        //stub
    }

    // MODFIES: this
    // EFFECTS: adds an outfit to list of outfits
    public void addOutfit(Outfit o){
        //stub
    }

    public List<Outfit> getOutfits(){
        return null; //stub
    }

    public List<Clothing> getClothing(){
        return null; //stub
    }

    public List<Clothing> getClothing(Clothing.ClothingType t){
        return null; //stub
    }
}
