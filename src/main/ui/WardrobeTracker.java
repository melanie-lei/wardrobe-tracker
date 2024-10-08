package ui;

import java.awt.Color;
import java.util.List;
import java.util.Scanner;

import model.Clothing;
import model.Clothing.ClothingType;
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
        String name;
        String description;
        Color colour;
        ClothingType type;
        String input;
        System.out.println("Name the piece: ");
        name = scanner.nextLine();
        System.out.println("Write a description: ");
        description = scanner.nextLine();
        System.out.println("Choose a colour. [1] Red | [2] Blue | [3] Green");
        input = scanner.nextLine();
        colour = chooseColour(input);
        System.out.println("Choose type. [1] Top | [2] Jacket | [3] Bottoms " + 
                            "| [4] Shoes | [5] Headwear | [6] Accessory");
        input = scanner.nextLine();
        type = chooseType(input);

        wardrobe.addClothing(new Clothing(type, colour, name, description));
    }

    // EFFECTS: processes user choice for type of clothing
    private ClothingType chooseType(String input) {
        switch (input) {
            case "1":
                return ClothingType.TOP;
            case "2":
                return ClothingType.JACKET;
            case "3":
                return ClothingType.BOTTOMS;
            case "4":
                return ClothingType.SHOES;
            case "5":
                return ClothingType.HEADWEAR;
            case "6":
                return ClothingType.ACCESSORY;
            default:
                System.out.println("Invalid input. Please try again.");
                input = scanner.nextLine();
                return chooseType(input);
        }
    }

    // EFFECTS: processes user choice for colour of clothing
    private Color chooseColour(String input) {
        switch (input) {
            case "1":
                return Color.red;
            case "2":
                return Color.blue;
            case "3":
                return Color.green;
            default:
                System.out.println("Invalid input. Please try again.");
                input = scanner.nextLine();
                return chooseColour(input);
        }
    }

    // EFFECTS: adds an outfit to the wardrobe
    private void addOutfit() {
        Outfit outfit = new Outfit();
        System.out.println("Name the outfit: ");
        outfit.setName(scanner.nextLine());
        System.out.println("Write a description: ");
        outfit.setDescription(scanner.nextLine());
        System.out.println("Choose top: ");
        outfit.setTop(chooseClothing(wardrobe.getTops()));
        System.out.println("Choose jacket: ");
        outfit.setJacket(chooseClothing(wardrobe.getJackets()));
        System.out.println("Choose bottoms: ");
        outfit.setBottoms(chooseClothing(wardrobe.getBottoms()));
        System.out.println("Choose shoes: ");
        outfit.setShoes(chooseClothing(wardrobe.getShoes()));
        System.out.println("Choose headwear: ");
        outfit.setHeadwear(chooseClothing(wardrobe.getHeadwear()));
        System.out.println("Choose accessory: ");
        outfit.addAccessory(chooseClothing(wardrobe.getAccessories()));
        wardrobe.addOutfit(outfit);
    }

    private Clothing chooseClothing(List<Clothing> listOfClothing) {
        for(int i = 0; i < listOfClothing.size(); i++){
            System.out.println("[" + String.valueOf(i+1) + "] " + listOfClothing.get(i).getName());
        }
        String input = scanner.nextLine();
        try {
            return listOfClothing.get(Integer.valueOf(input) - 1).wear();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            return chooseClothing(listOfClothing);
        }
    }

    // EFFECTS: displays all clothing and presents options for user to view specific item
    private void viewClothing() {
        for(int i = 0; i < clothing.size(); i++){
            System.out.println("[" + String.valueOf(i+1) + "] " + clothing.get(i).getName());
        }
        System.out.println("Choose a clothing item to view, [S]ort, or [E]xit");
        String input = scanner.nextLine().toUpperCase();

        if(input.equals("E")){
            return;
        }
        if(input.equals("S")){
            sortMenu();
        } else {
            handleViewInput("clothing", input);
        }
    }

    private void sortMenu() {
        System.out.println("Sort by [1] Alphabetical | [2] Total times worn ascending | [3] Total times worn descending"
                            + " | [4] Times worn since wash | [5] Is favourite");
        String input = scanner.nextLine().toUpperCase();

        boolean isInvalid = false;
        do{
            isInvalid = false;
            switch (input) {
                case "1":
                    wardrobe.sortClothing(clothing, "alphabetical");
                    viewClothing();
                    break;
                case "2":
                    wardrobe.sortClothing(clothing, "totalTimesWornAscending");
                    viewClothing();
                    break;
                case "3":
                    wardrobe.sortClothing(clothing, "totalTimesWornDescending");
                    viewClothing();
                    break;
                case "4":
                    wardrobe.sortClothing(clothing, "totalTimesWornDescending");
                    viewClothing();
                    return;
                case "5":
                    wardrobe.sortClothing(clothing, "isFavourite");
                    viewClothing();
                    return;
                default:
                    isInvalid = true;
                    System.out.println("Invalid Input. Try again.");
                    input = scanner.nextLine().toUpperCase();
                    break;
            }
            
        }while(isInvalid);
    }

    // REQUIRES: option must be "clothing" or "outfit"
    // EFFECTS: takes user's input on the view clothing or outfit page
    private void handleViewInput(String option, String input){
        try {
            if(input.equals("E")){
                return;
            }
            if(option.equals("clothing")) {
                displayClothingItem(clothing.get(Integer.valueOf(input) - 1));
            } else {
                displayOutfit(outfits.get(Integer.valueOf(input) - 1));
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
        System.out.println("Colour: " + String.valueOf(clothingItem.getColour()));
        System.out.println("Times worn total: " + String.valueOf(clothingItem.getTotalTimesWorn()));
        System.out.println("Times worn since last wash: " + String.valueOf(clothingItem.getTimesWornSinceWash()));
        System.out.println("Is favourite: " + clothingItem.isFavourite());

        System.out.println("[F]avourite, [R]emove Favourite, [W]ash, [E]xit");
        String input = scanner.nextLine().toUpperCase();

        boolean isInvalid = false;
        do{
            isInvalid = false;
            switch (input) {
                case "F":
                    clothingItem.setFavourite(true);
                    System.out.println("Set as a favourite.");
                    displayClothingItem(clothingItem);
                    break;
                case "R":
                    clothingItem.setFavourite(false);
                    System.out.println("Removed as a favourite.");
                    displayClothingItem(clothingItem);
                    break;
                case "W":
                    clothingItem.wash();
                    System.out.println("Item washed.");
                    displayClothingItem(clothingItem);
                    break;
                case "E":
                    return;
                default:
                    isInvalid = true;
                    System.out.println("Invalid Input. Try again.");
                    input = scanner.nextLine().toUpperCase();
                    break;
            }
            
        } while(isInvalid);

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
