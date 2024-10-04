package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A wardrobe which consists of Outfits and Clothing items organized by type
public class Wardrobe {

    private List<Clothing> clothing;
    private List<Clothing> tops;
    private List<Clothing> jackets;
    private List<Clothing> bottoms;
    private List<Clothing> shoes;
    private List<Clothing> headwear;
    private List<Clothing> accessories;
    private List<Outfit> outfits;
    
    // EFFECTS: Creates a Wardrobe with no outfits or clothing items
    public Wardrobe(){
        this.clothing = new ArrayList<Clothing>();
        this.tops = new ArrayList<Clothing>();
        this.jackets = new ArrayList<Clothing>();
        this.bottoms = new ArrayList<Clothing>();
        this.shoes = new ArrayList<Clothing>();
        this.headwear = new ArrayList<Clothing>();
        this.accessories = new ArrayList<Clothing>();
        this.outfits = new ArrayList<Outfit>();
    }

    // REQUIRES: category can only be:
    // "totalTimesWornAscending", "totalTimesWornDescending", "timesWornSinceWash", "isFavourite", "alphabetical"
    // MODIFIES: list
    // EFFECTS: sorts the given list based on the given category
    public void sortClothing(List<Clothing> list, String category){
        switch (category) {
            case "alphabetical":
                list.sort((o1, o2)-> o1.getName().compareTo(o2.getName()));
                break;
            case "totalTimesWornAscending":
                list.sort((o1, o2)-> o1.getTotalTimesWorn()-o2.getTotalTimesWorn());
                break;
            case "totalTimesWornDescending":
                list.sort((o1, o2)-> o1.getTotalTimesWorn()-o2.getTotalTimesWorn());
                Collections.reverse(list);
                break;
            case "timesWornSinceWash":
                list.sort((o1, o2)-> o1.getTimesWornSinceWash()-o2.getTimesWornSinceWash());
                break;
            case "isFavourite":
                list.sort((o1, o2)-> favouriteSort(o1.isFavourite(), o2.isFavourite()));
                break;
            default:
                break;
        }
    }

    private int favouriteSort(boolean a, boolean b){
        if (a && !b) {
            return -1;
        } else if (b && !a){
            return 1;
        } else {
            return 0;
        }
    }

    // MODFIES: this
    // EFFECTS: adds an outfit to list of outfits
    public void addOutfit(Outfit o){
        this.outfits.add(o);
    }

    // MODFIES: this
    // EFFECTS: adds a clothing to list of clothing
    public void addClothing(Clothing c){
        this.clothing.add(c);
        switch (c.getClothingType()) {
            case TOP:
                this.tops.add(c);
                break;
            case JACKET:
                this.jackets.add(c);
                break;
            case BOTTOMS:
                this.bottoms.add(c);
                break;
            case SHOES:
                this.shoes.add(c);
                break;
            case HEADWEAR:
                this.headwear.add(c);
                break;
            case ACCESSORY:
                this.accessories.add(c);
                break;
            default:
                break;
        }
    }

    public List<Outfit> getOutfits(){
        return this.outfits;
    }

    public List<Clothing> getClothing(){
        return this.clothing;
    }

    public List<Clothing> getTops(){
        return this.tops;
    }

    public List<Clothing> getJackets(){
        return this.jackets;
    }

    public List<Clothing> getBottoms(){
        return this.bottoms;
    }

    public List<Clothing> getHeadwear(){
        return this.headwear;
    }

    public List<Clothing> getShoes(){
        return this.shoes;
    }

    public List<Clothing> getAccessories(){
        return this.accessories;
    }
}
