package dal.mapleLeafs.shelve12.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dal.mapleLeafs.shelve12.model.UserModel;

/**
 * This class will be used
 * to insert the coins count
 * in the database
 */
public class UserDatabase {
    private final UserDataQueries userDataQueries;
    private List<UserModel> userList = new ArrayList<>();

    public UserDatabase(Context context){
        userDataQueries = new UserDataQueries(context);
        userDataQueries.open();
        userDataQueries.isAppRunningFirstTime();
        userList = userDataQueries.getUserData();
        if(null!=userList && userList.size()>0) {
            for(int i=0;i<userList.size();i++) {
                UserModel model = userList.get(i);
            }
        }
    }

    /**
     * Closing the queries
     */
    public void closeDatabase(){
        userDataQueries.close();
    }


    /**
     * @param userModel
     * @return
     * This method will
     * update the coins of a user
     */
    public boolean updateUser(UserModel userModel){
        return userDataQueries.updateUser(userModel);
    }


    public UserModel selectUser(){
        return userDataQueries.selectUser("Manpreet");
    }

}
