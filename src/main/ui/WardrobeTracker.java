package ui;

import java.util.List;
import java.util.Scanner;

import model.Clothing;
import model.Outfit;
import model.Wardrobe;

public class WardrobeTracker {

    private Wardrobe wardrobe;
    private Scanner scanner;
    private boolean isRunning;
    private List<Clothing> clothing;
    private List<Outfit> outfits;

    // EFFECTS: creates a wardrobe tracker and initiates the application
    WardrobeTracker(){
        wardrobe = new Wardrobe();
        scanner = new Scanner(System.in);
        clothing = wardrobe.getClothing();
        outfits = wardrobe.getOutfits();
        isRunning = true;
        System.out.println("Building wardrobe...");

        while(this.isRunning){
            menuHandler();
        }
    }

    // EFFECTS: displays menu and retrieves user input
    private void menuHandler() {
        displayMenu();
        String input = this.scanner.nextLine();
        processCommand(input.toUpperCase());
    }

    // EFFECTS: displays menu options
    private void displayMenu() {
        System.out.println("[1] - Add clothing item");
        System.out.println("[2] - Add outfit");
        System.out.println("[3] - View clothing");
        System.out.println("[4] - View outfits");
        System.out.println("[E] - Exit application");
    }

    // EFFECTS: processes user input
    private void processCommand(String input) {
        switch (input) {
            case "1":
                addClothingItem();
                break;
            case "2":
                addOutfit();
                break;
            case "3":
                viewClothing();
                break;
            case "4":
                viewOutfits();
                break;

            case "E":
                exitApplication();
                break;
            default:
            System.out.println("Invalid input. Please try again.");
                break;
        }
    }

    // EFFECTS: adds a clothing item to the wardrobe
    private void addClothingItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClothingItem'");
    }

    // EFFECTS: adds an outfit to the wardrobe
    private void addOutfit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOutfit'");
    }

    // EFFECTS: displays all clothing and presents options for user to view specific item
    private void viewClothing() {
        for(int i = 0; i < clothing.size(); i++){
            System.out.println(clothing.get(i).getName());
        }
        System.out.println("Choose a clothing item to view or [E]xit");
        String input = scanner.nextLine().toUpperCase();

        if(input.equals("E")){
            return;
        }
        handleViewInput("clothing", input);
    }

    // REQUIRES: option must be "clothing" or "outfit"
    // EFFECTS: takes user's input on the view clothing or outfit page
    private void handleViewInput(String option, String input){
        try {
            if(input.equals("E")){
                return;
            }
            if(option.equals("clothing")) {
                displayClothingItem(clothing.get(Integer.valueOf(input)));
            } else {
                displayOutfit(outfits.get(Integer.valueOf(input)));
            }
            if(Integer.valueOf(input) == 0){
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            String newInput = scanner.nextLine().toUpperCase();
            handleViewInput(option, newInput);
        }
    }

    // EFFECTS: displays properties of the clothing item and provides an option to wash
    private void displayClothingItem(Clothing clothingItem) {
        System.out.println("Name: " + clothingItem.getName());
        System.out.println("Description: " + clothingItem.getDescription());
        System.out.println("Colour: " + String.valueOf(clothingItem.getColour().getRGB()));
        System.out.println("Times worn total: " + String.valueOf(clothingItem.getTotalTimesWorn()));
        System.out.println("Times worn since last wash: " + String.valueOf(clothingItem.getTimesWornSinceWash()));

        System.out.println("[W]ash, [E]xit");
        String input = scanner.nextLine().toUpperCase();

        boolean invalidInput = true;
        while(invalidInput){
            switch (input) {
                case "W":
                    invalidInput = false;
                    clothingItem.wash();
                    displayClothingItem(clothingItem);
                    break;
                case "E":
                    invalidInput = false;
                    return;
                default:
                    System.out.println("Invalid Input. Try again.");
                    input = scanner.nextLine().toUpperCase();
                    break;
            }
        }

    }

    // EFFECTS: displays all outfits and presents options for user to view specific item
    private void viewOutfits() {
        for(int i = 0; i < outfits.size(); i++){
            System.out.println(outfits.get(i).getName());
        }
        System.out.println("Choose a outfit to view or [E]xit");
        String input = scanner.nextLine().toUpperCase();

        if(input.equals("E")){
            return;
        }
        handleViewInput("outfit", input);
    }

    // EFFECTS: displays properties of an outfit
    private void displayOutfit(Outfit outfit) {
        System.out.println("Name: " + outfit.getName());
        System.out.println("Description: " + outfit.getDescription());
        System.out.println("Headwear: " + outfit.getHeadwear().getName());
        System.out.println("Top: " + outfit.getTop().getName());
        System.out.println("Jacket: " + outfit.getJacket().getName());
        System.out.println("Bottoms: " + outfit.getBottoms().getName());
        System.out.println("Shoes: " + outfit.getShoes().getName());
        System.out.print("Accessories: ");
        for (Clothing a : outfit.getAccessories()) {
            System.out.print(a.getName() + ", ");
        }
        System.out.println();

        System.out.println("[E]xit");
        while(!scanner.nextLine().toUpperCase().equals("E")){
            System.out.println("Invalid Input. Try again.");
        }
    }

    // EFFECTS: ends the application
    private void exitApplication() {
        System.out.println("Closing wardrobe.");
        this.isRunning = false;
    }

}
