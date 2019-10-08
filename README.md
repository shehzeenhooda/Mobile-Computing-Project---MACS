# Maple Leafs

# Identification

Shelve 12
1. B00824331: Jay Sharma - jay.sharma@dal.ca (Leader)
2. B00821998: Manpreet Singh - manpreet.singh@dal.ca
3. B00792805: Alfred Nketia - al702002@dal.ca
4. B00820613: Abhinandan Walia - ab403576@dal.ca
5. B00812551: Shehzeen Huda - sh655624@dal.ca
6. B00826918: Rishabh Dhawan - rs666810@dal.ca

Link to Git repository:
https://git.cs.dal.ca/jays/shelve12

Information on Individual Contributions:
https://git.cs.dal.ca/jays/shelve12/blob/master/CONTRIBUTING.md

# Project Summary
'Shelve-12' is a card exchange game, which has been designed for NHL fans. The user will initially get few cards to compete against the system and on the basis of the NHL player ranking, user will either win a card from the system or loose the card to it during a competition match. User can see some basic statistics related to his gameplay like the coins he has won, his level, medals and teams on the dashboard itself. Additionally, the user can purchase cards using his coins from the XII Store. We have added a navigation bar with menu items that include a Dashboard, XII Store, Medals, Tutorial and Terms. We have also provided a QR Code screen that generates a unique QR for the userID.

## Libraries

**AndroidX:** Latest updated Android Support Library. Source [here](https://developer.android.com/reference/androidx/packages)

**QRGenerator:** Helps generating QR Code for a given string. Source [here](https://github.com/androidmads/QRGenerator)

## Installation Notes
Minimum Android SDK version required: 23

## Code Examples

**Problem 1: Issues in implementing database**

Database implementation had problems like we could not fetch the created table. So initially we created a pipeline to between play and dashboard activities. We later refrred to Lab tutorials for correct implementation. We needed a method to fetch the user data from the database on the basis of user name

```
// The method we implemented that solved our problem

public UserModel selectUser(String  userName){
        int coinsCount=0;
        String user="";
        UserModel userModel = new UserModel(coinsCount,user);
        Cursor cursor = database.rawQuery("SELECT * FROM user WHERE user_name = ?", new String[]{userName});
        if(cursor.moveToFirst()){
            userModel.setCoinsCount(cursor.getInt(1));
            userModel.setUserName(cursor.getString(2));
        }
        return  userModel;
    }

// Source: Lab 5
```

**Problem 2: Issues in fetching and updating the database**

We needed a method to fetch the update the user data in the database on the basis of user name

```
// The method we implemented that solved our problem

public boolean updateUser(UserModel userModel){
        ContentValues cv= new ContentValues();
        cv.put(UserDbHelper.UserEntry.COLUMN_USER_NAME,userModel.getUserName());
        cv.put(UserDbHelper.UserEntry.COLUMN_COINS_COUNT,userModel.getCoinsCount());
        try {

            return database.update(UserDbHelper.UserEntry.TABLE_NAME, cv,
                    UserDbHelper.UserEntry.COLUMN_USER_NAME + " = 'Manpreet'",
                    null) > 0;
        }catch(Exception e){
            e.getMessage();
        }
        return false;
    }
// Source: https://developer.android.com/training/data-storage/sqlite#java
```

## Feature Section

1. Dashboard
	- Statistics visible showing Medals, Coins, Teams
	- Play Forward FAB
2. PlayScreen
	- User gets 6 random cards to play with
	- System gets the remaining set of cards
	- Cards can be exchanged on the basis of ranks
	- A haptic feedback is given on winning a card
	- The same card cannot be shelved again
	- Database is updated with user coins, and medals
3. XII Store
	- Shows the available coins for the user from Database
	- Ability to purchase cards through available coins
4. Medals
	- Shows the current number of medals won by the user
5. Tutorial
	- Shows how to play Shelve 12
6. Terms
	- Shows Terms and Conditions that the user must agree
	- USer can save the terms accepting the conditions
7. QR Code
	- Shows a QR code for user's ID

## Final Project Status
As proposed, project successfully implemented all of the features within the best interest of the users to provide them with an immersive and a unique game. Future of the application inspires the team on encompassing all the bonus functionalities with multiplayer support at top of the priority list. Almost all the proposed goals were complemented.

The next steps, if the project were to continue:
- Change the terms to appear as the splash screen
- A recycler view to exchange cards
- Exchange of cards through QR code
- Google Ads to be displayed
- More teams and members to be added
- More levels to be added

#### Minimum Functionality (from Proposal)

- The minimum functionality requires a main game screen with a menu (Completed)
- Comparison and exchange of cards should work (Completed)
- A Store should be present where the user can see his coins (Completed)
- User should be able to see some statistics (Completed)
- A QR code should generate (Completed)

#### Expected Functionality

- The exchange of cards should also result in earning coins or losing a card (Completed)
- The current level, total number of coins and cards earned should be maintained (Completed)
- Current set of cards, and coins should be maintained separately (Completed)
- After collecting all cards of a team, the user should level up (Partially Completed)
- User should be able to purchase a card from the Store using virtual coins (Partially Completed) - database not updating yet
- User should be able to share a card through QR code (Not Implemented)

#### Bonus Functionality

- User can reset the game and statistics (Completed)
- Game can have some music with an on/off switch (Removed)
- A vibration/haptic feedback on winning a card (Completed)
- Adding terms and conditions (Completed)
- A tutorial on How to play a game (Completed)
- Adding a picture to every card face (Completed)
- A multiplayer mode utilizing GPS to find players nearby and compete or a multiplayer mode (Not Implemented)

## Sources

- Images
	- [1] NFL Players. Available: https://www.nfl.com/
	- [2] Medal. Available: http://pngimg.com/download/57744
	- [3] Coin. Available: http://pngimg.com/imgs/objects/coin/
	- [4] Material Design icons. Available: https://material.io/tools/icons/?style=baseline
- Design guides
	- [5] BottomAppBars. Available: https://material.io/design/components/app-bars-bottom.html
	- [6] CardViews. Available: https://material.io/design/components/cards.html#
- Programming tutorials
	- [7] Horizontal Scroll View. Available: https://questdot.com/android-image-gallery-horizontalscrollview-tutorial/
	- [8] SQLite. Available: https://developer.android.com/training/data-storage/sqlite#java
	- [9] BottomAppBar. Available: https://medium.com/material-design-in-action/implementing-bottomappbar-material-components-for-android-f490c4a01708
	- [10] Generating QR Code. Available: https://www.c-sharpcorner.com/article/how-to-generate-qr-code-in-android/
	- [11] Raw SQL Query. Available: https://developer.android.com/reference/android/arch/persistence/room/RawQuery
- Research material
	- [12] Hashmap vs. LinkedHashMap. Available: http://www.java67.com/2012/08/difference-between-hashmap-and-LinkedHashMap-Java.html
- Android libraries
	- [13] AndroidX. Available: https://developer.android.com/reference/androidx/packages
	- [14] QRGenerator.  Available: https://github.com/androidmads/QRGenerator
