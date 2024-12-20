package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// An outfit which is made up of Clothing items
public class Outfit implements Writable {

    private Clothing top;
    private Clothing jacket;
    private Clothing bottoms;
    private Clothing shoes;
    private Clothing headwear;
    private List<Clothing> accessories;
    private String name;
    private String description;

    // Effects: creates and Outfit with no Clothing items
    public Outfit() {
        accessories = new ArrayList<Clothing>();
    }

    // Modifies: this
    // Effects: wears all clothing in the outfits
    public Outfit saveOutfit() {
        EventLog.getInstance().logEvent(new Event("Outfit saved."));
        this.top.wear();
        this.jacket.wear();
        this.bottoms.wear();
        this.shoes.wear();
        this.headwear.wear();
        for (Clothing a : this.accessories) {
            a.wear();
        }
        return this;
    }

    // REQUIRES: c has a type ACCESSORY
    // MODIFIES: this
    // EFFECTS: adds an accessory clothing to current accessories
    public void addAccessory(Clothing c) {
        accessories.add(c);
    }

    // REQUIRES: c has a type ACCESSORY
    // MODIFIES: this
    // EFFECTS: removes an accessory clothing from current accessories
    public void removeAccessory(Clothing c) {
        accessories.remove(c);
    }

    // REQUIRES: c has a type TOP
    public void setTop(Clothing c) {
        this.top = c;
    }

    // REQUIRES: c has a type JACKET
    public void setJacket(Clothing c) {
        this.jacket = c;
    }

    // REQUIRES: c has a type BOTTOMS
    public void setBottoms(Clothing c) {
        this.bottoms = c;
    }

    // REQUIRES: c has a type SHOES
    public void setShoes(Clothing c) {
        this.shoes = c;
    }

    // REQUIRES: c has a type HEADWEAR
    public void setHeadwear(Clothing c) {
        this.headwear = c;
    }

    public Clothing getTop() {
        return this.top;// stub
    }

    public Clothing getJacket() {
        return this.jacket;// stub
    }

    public Clothing getBottoms() {
        return this.bottoms;// stub
    }

    public Clothing getShoes() {
        return this.shoes;// stub
    }

    public Clothing getHeadwear() {
        return this.headwear;// stub
    }

    public List<Clothing> getAccessories() {
        return this.accessories;// stub
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("description", this.description);
        json.put("top", this.top.getId());
        json.put("jacket", this.jacket.getId());
        json.put("bottoms", this.bottoms.getId());
        json.put("shoes", this.shoes.getId());
        json.put("headwear", this.headwear.getId());
        JSONArray jsonArray = new JSONArray();
        for (Clothing c : accessories) {
            jsonArray.put(c.getId());
        }
        json.put("accessories", jsonArray);
        return json;
    }

}
