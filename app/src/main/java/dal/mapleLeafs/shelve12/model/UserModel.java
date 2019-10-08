package dal.mapleLeafs.shelve12.model;

/**
 * This class will be
 * used as a model to
 * store the coins data
 */
public class UserModel {

    private int coinsCount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public UserModel(int coinsCount,String userName) {
        this.coinsCount=coinsCount;
        this.userName=userName;
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

}
