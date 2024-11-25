# Fit Tracker

## Summary

Fit Tracker is a wardrobe and outfit tracker to keep track of your style.
A user who wishes to keep track of their wardrobe and outfits will be able to manually record and see their past outfits. The application also provides summaries on individual items of clothing owned.

An outfit consists of:
- Hat
- Accessory(s)
- Top
- Jacket
- Bottoms
- Shoes

Each individual item keeps track of:
- Name/title
- Representative colour
- Description
- Times worn total
- Times worn since last wash

## User Stories

- As a user, I want to be able to add a clothing item to my wardrobe (list of clothing items)
- As a user, I want to be able to select clothing items to create an outfit
- As a user, I want to be able to save an outfit
- As a user, I want to be able to see a history of past outfits worn
- As a user, I want to be able to indicate when I wash an item of clothing
- As a user, I want to be able to view info on an item of clothing, eg. total times worn, times worn since last wash
- As a user, I want to be able to sort my clothing items based on its properties
- As a user, I want to be able to save my outfits and clothing items to file (if I so choose)
- As a user, I want to be able to be able to load my outfits and clothing items from file (if I so choose)

# Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting 'New', specifying details for the clothing item, and hitting 'Add'
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting 'Sort A -> Z' or 'Sort Worn'
- You can locate my visual component by selecting a clothing item and hitting 'View'
- You can save the state of my application by selecting 'Save File'
- You can reload the state of my application by selecting 'Load File'

## Phase 4: Task 2
- Mon Nov 25 10:11:13 PST 2024
- Added TOP to wardrobe.
- Mon Nov 25 10:11:15 PST 2024
- Added BOTTOMS to wardrobe.
- Mon Nov 25 10:11:16 PST 2024
- Sorted clothing by alphabetical.
- Mon Nov 25 10:11:17 PST 2024
- Sorted clothing by totalTimesWornDescending.

## Phase 4: Task 3
If I had more time for this project, I would refactor Clothing into an abstract class called Wearable and have the different Clothing Types (Top, Jacket, etc.) extends the Wearable class. Outfits will consist of a collection of Wearables. This will allow more flexibility in the future to build different Outfits and also implement different functionality in the Clothing pieces. For example, right now, accessories do not count times worn since last wash using a conditional block. If the different Clothing items all extended Wearable, I could override the wear() method and not increment times worn since last wash. 