package dal.mapleLeafs.shelve12.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Set;

public class GameLogic {

    public static int cardid;       //to store card key selected by user from his map
    public Integer cardvalue;       //to store card value selected by user from his m
    public Integer syskey;          //to store card key selected by System player from his map
    public int sysvalue;            //to store card value selected by systemplayer from his map
    public LinkedHashMap<Integer, Integer> sysplayer;   //to store Map for the system
    public LinkedHashMap<Integer, Integer> userplayer;  //to store Map for the system
    public LinkedHashMap<Integer, Integer> sysSelection;
    public List<Integer> shuffledKeys;                  // for storing hashmap keys after shuffling
    public LinkedHashMap<Integer, Integer> shuffledGlobalCards;
    public LinkedHashMap<Integer, Integer> selectedSysplayerCards;  // for first six cards selected for user to activity_play with

    //Initial Global player. Must be called onload the page

    public void setSysplayer() {
        sysplayer = new LinkedHashMap<Integer, Integer>();
        sysplayer.put(9001, 1);
        sysplayer.put(9002, 5);
        sysplayer.put(9003, 6);
        sysplayer.put(9004, 10);
        sysplayer.put(9005, 12);
        sysplayer.put(9006, 15);
        sysplayer.put(9007, 20);
        sysplayer.put(9008, 23);
        sysplayer.put(9009, 24);
        sysplayer.put(9010, 25);
        sysplayer.put(9011, 30);
        sysplayer.put(9012, 31);
        sysplayer.put(9013, 35);
        sysplayer.put(9014, 36);
        sysplayer.put(9015, 40);
        sysplayer.put(9016, 44);
        sysplayer.put(9017, 50);
        sysplayer.put(9018, 38);
        sysplayer.put(9019, 39);
        sysplayer.put(9020, 50);
        sysplayer.put(9021, 55);
        sysplayer.put(9022, 60);
    }

    //  Shuffling the global map before six is selected for the user player. Must be called on load time
    public LinkedHashMap<Integer, Integer> shuffleMap() {
        shuffledGlobalCards = new LinkedHashMap<Integer, Integer>();
        List<Integer> cardShuffling = new ArrayList(sysplayer.keySet());
        Collections.shuffle(cardShuffling);
        shuffledKeys = new ArrayList();

        for (Integer thisIndex : cardShuffling) {
            shuffledKeys.add(thisIndex);
            shuffledGlobalCards.put(thisIndex, sysplayer.get(thisIndex));
        }
        return shuffledGlobalCards;
    }

    //  assigning six cards from the global shuffled cards. Must be called onload the web page
    public LinkedHashMap<Integer, Integer> setUserplayer() {

        userplayer = new LinkedHashMap<Integer, Integer>();
        int increasecount = 1;
        Iterator<Entry<Integer, Integer>> sysplayerEntries = shuffledGlobalCards.entrySet().iterator();
        while (increasecount < 7 && sysplayerEntries.hasNext()) {

            Entry<Integer, Integer> firstRecord = sysplayerEntries.next();
            userplayer.put(firstRecord.getKey(), firstRecord.getValue());
            increasecount++;
        }
        return userplayer;
    }

//  remove the six cards assigned to userplayer from the global shuffled cards. Must be called on load
    public void removeUserAssignedCardsfromSysplayer() {
        Set<Integer> userPlayerKeys = userplayer.keySet();
        for (Integer key : userPlayerKeys) {
            shuffledGlobalCards.remove(key);
        }
    }


//    call this method when displaying six cards selected for system player to activity_play with. Use IDs obtained from this function
//    this method to assign to cards for the players

    public LinkedHashMap<Integer, Integer> getFirstSixGlobalShuffledCards() {
        List<Integer> globalplayerset = new ArrayList(shuffledGlobalCards.keySet());
        selectedSysplayerCards = new LinkedHashMap<Integer, Integer>();
        int countsixplayers = 1;
        while (countsixplayers < 7) {
            for (Integer index : globalplayerset) {
                selectedSysplayerCards.put(index, shuffledGlobalCards.get(index));
            }
            countsixplayers++;
        }
        return selectedSysplayerCards;
    }

    public Integer onClickCardToPlay(Integer actualcardid) {
        cardvalue = userplayer.get(actualcardid);
        Entry<Integer, Integer> selectFirst = selectedSysplayerCards.entrySet().iterator().next(); //declare variable to store first key and value from hash map
        syskey = selectFirst.getKey(); //get key of first map
        sysvalue = selectFirst.getValue();
        // get value of card selected by user
        return cardvalue; //return method so we can access it's variables... we can choose to return the specific variables
    }
}
