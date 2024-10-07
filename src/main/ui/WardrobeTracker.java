package ui;

import java.util.Scanner;

import model.Wardrobe;

public class WardrobeTracker {

    private Wardrobe wardrobe;
    private Scanner scanner;
    private boolean isRunning;

    // EFFECTS: creates a wardrobe tracker and initiates the application
    WardrobeTracker(){
        wardrobe = new Wardrobe();
        scanner = new Scanner(System.in);
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
        processCommand(input);
    }

    // EFFECTS: displays menu options
    private void displayMenu() {
        System.out.println("[1] - Add clothing item");
        System.out.println("[2] - Add outfit");
        System.out.println("[3] - View clothing");
        System.out.println("[4] - View outfits");
        System.out.println("[5] - Exit application");
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
            case "5":
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewClothing'");
    }

    // EFFECTS: displays all outfits and presents options for user to view specific item
    private void viewOutfits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewOutfits'");
    }

    private void exitApplication() {
        System.out.println("Closing wardrobe.");
        this.isRunning = false;
    }

}
