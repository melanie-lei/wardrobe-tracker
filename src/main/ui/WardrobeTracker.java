package ui;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Clothing;
import model.Clothing.ClothingType;
import model.Outfit;
import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WardrobeTracker {

    private Wardrobe wardrobe;
    private Scanner scanner;
    private boolean isRunning;
    private List<Clothing> clothing;
    private List<Outfit> outfits;
    private static final String JSON_PATH = "./data/wardrobe.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int idTracker;

    // EFFECTS: creates a wardrobe tracker and initiates the application
    WardrobeTracker() {
        wardrobe = new Wardrobe();
        scanner = new Scanner(System.in);
        clothing = wardrobe.getClothing();
        outfits = wardrobe.getOutfits();
        isRunning = true;
        idTracker = 0;
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        System.out.println("Building wardrobe...");

        while (this.isRunning) {
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
        System.out.println("Name the piece: ");
        String name = scanner.nextLine();
        System.out.println("Write a description: ");
        String description = scanner.nextLine();
        System.out.println("Choose a colour. [1] Red | [2] Blue | [3] Green");
        String input = scanner.nextLine();
        Color colour = chooseColour(input);
        System.out.println("Choose type. [1] Top | [2] Jacket | [3] Bottoms "
                + "| [4] Shoes | [5] Headwear | [6] Accessory");
        input = scanner.nextLine();
        ClothingType type = chooseType(input);

        wardrobe.addClothing(new Clothing(type, colour, name, description, idTracker++));
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

    // EFFECTS: displays list of clothing and processes user's choice and returns
    // chosen clothing
    private Clothing chooseClothing(List<Clothing> listOfClothing) {
        for (int i = 0; i < listOfClothing.size(); i++) {
            System.out.println("[" + String.valueOf(i + 1) + "] " + listOfClothing.get(i).getName());
        }
        String input = scanner.nextLine();
        try {
            return listOfClothing.get(Integer.valueOf(input) - 1).wear();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            return chooseClothing(listOfClothing);
        }
    }

    // EFFECTS: displays all clothing and presents options for user to view specific
    // item
    private void viewClothing() {
        for (int i = 0; i < clothing.size(); i++) {
            System.out.println("[" + String.valueOf(i + 1) + "] " + clothing.get(i).getName());
        }
        System.out.println("Choose a clothing item to view, [S]ort, or [E]xit");
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("E")) {
            return;
        }
        if (input.equals("S")) {
            sortMenu();
        } else {
            handleViewInput("clothing", input);
        }
    }

    // Show menu for sorting wardrobe and process requests
    private void sortMenu() {
        System.out.println("Sort by [1] Alphabetical | [2] Total times worn ascending | [3] Total times worn descending"
                + " | [4] Times worn since wash | [5] Is favourite");
        String input = scanner.nextLine().toUpperCase();

        List<String> sortOptions = Arrays.asList("alphabetical", "totalTimesWornAscending", "totalTimesWornDescending",
                "timesWornSinceWash", "isFavourite");

        boolean isInvalid = false;
        do {
            isInvalid = false;
            try {
                wardrobe.sortClothing(clothing, sortOptions.get(Integer.valueOf(input)));
            } catch (Exception e) {
                isInvalid = true;
                System.out.println("Invalid Input. Try again.");
                input = scanner.nextLine().toUpperCase();
            }

        } while (isInvalid);
        viewClothing();
    }

    // REQUIRES: option must be "clothing" or "outfit"
    // EFFECTS: takes user's input on the view clothing or outfit page
    private void handleViewInput(String option, String input) {
        try {
            if (input.equals("E")) {
                return;
            }
            if (option.equals("clothing")) {
                displayClothingItem(clothing.get(Integer.valueOf(input) - 1));
            } else {
                displayOutfit(outfits.get(Integer.valueOf(input) - 1));
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            String newInput = scanner.nextLine().toUpperCase();
            handleViewInput(option, newInput);
        }
    }

    // EFFECTS: displays properties of the clothing item and provides an option to
    // wash
    private void displayClothingItem(Clothing clothingItem) {
        System.out.println("Name: " + clothingItem.getName());
        System.out.println("Description: " + clothingItem.getDescription());
        System.out.println("Colour: " + String.valueOf(clothingItem.getColour()));
        System.out.println("Times worn total: " + String.valueOf(clothingItem.getTotalTimesWorn()));
        System.out.println("Times worn since last wash: " + String.valueOf(clothingItem.getTimesWornSinceWash()));
        System.out.println("Is favourite: " + clothingItem.isFavourite());

        System.out.println("[F]avourite, [R]emove Favourite, [W]ash, [E]xit");
        String input = scanner.nextLine().toUpperCase();

        handleClothingInput(input, clothingItem);

    }

    private void handleClothingInput(String input, Clothing clothingItem) {
        switch (input) {
            case "F":
                clothingItem.setFavourite(true);
                System.out.println("Set as a favourite.");
                break;
            case "R":
                clothingItem.setFavourite(false);
                System.out.println("Removed as a favourite.");
                break;
            case "W":
                clothingItem.wash();
                System.out.println("Item washed.");
                break;
            case "E":
                return;
            default:
                System.out.println("Invalid Input. Try again.");
                input = scanner.nextLine().toUpperCase();
                handleClothingInput(input, clothingItem);
                return;
        }
        displayClothingItem(clothingItem);
    }

    // EFFECTS: displays all outfits and presents options for user to view specific
    // item
    private void viewOutfits() {
        for (int i = 0; i < outfits.size(); i++) {
            System.out.println(outfits.get(i).getName());
        }
        System.out.println("Choose a outfit to view or [E]xit");
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("E")) {
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
        while (!scanner.nextLine().toUpperCase().equals("E")) {
            System.out.println("Invalid Input. Try again.");
        }
    }

    // EFFECTS: ends the application
    private void exitApplication() {
        System.out.println("Closing wardrobe.");
        this.isRunning = false;
    }

}
