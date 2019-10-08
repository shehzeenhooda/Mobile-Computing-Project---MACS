package dal.mapleLeafs.shelve12.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import androidx.cardview.widget.CardView;
import dal.mapleLeafs.shelve12.model.GameLogic;
import dal.mapleLeafs.shelve12.R;
import dal.mapleLeafs.shelve12.database.UserDatabase;
import dal.mapleLeafs.shelve12.database.UserDbHelper;
import dal.mapleLeafs.shelve12.model.UserModel;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class BuyCardActivity extends AppCompatActivity {

    private TextView textView;
    private final int vibrateWinMs = 500;
    private Vibrator hapticFeedback;
    private static CardView cardView1;
    public  FloatingActionButton leftSlide,rightSlide;
    public LinkedHashMap<Integer, Integer> shuffledMapUserCads;
    public List userCardList;

    private static TextView textView1;



    private int coins;
    public final static String pipe ="coins_val";

    private UserDatabase userDatabase;
    private GameLogic gameLogic;
    private Activity mActivity;
    private TextView CoinsCard;
    private TextView CoinsCardPrice;
    private Button buyButton;
    private int generalCoins;
    private int particularCoins;
    private UserDbHelper userDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_card);
        mActivity=this;
        CoinsCard = findViewById(R.id.CoinsCard);
        buyButton = findViewById(R.id.buyButton);
        CoinsCardPrice = findViewById(R.id.CoinsCardPrice);

        userDbHelper = new UserDbHelper(this);
        userDatabase = new UserDatabase(mActivity);

        UserModel userModel = userDatabase.selectUser();
        coins = userModel.getCoinsCount();
        UserModel user= new UserModel(coins,"Manpreet");
        userDatabase.updateUser(user);


        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back);
        leftSlide = (FloatingActionButton) findViewById(R.id.backslideleft);
        rightSlide = (FloatingActionButton) findViewById(R.id.backslideright);

        leftSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomFunctionCall();

            }
        });

        rightSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomFunctionCall();

            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("general : ", String.valueOf(generalCoins));
                Log.i("particular coin : ", String.valueOf(particularCoins));

               // Toast.makeText(getApplicationContext(),String.valueOf(particularCoins), Toast.LENGTH_SHORT).show();

                if(generalCoins > particularCoins){

                    Integer calc = generalCoins - particularCoins;
                    coins = calc;
                    UserModel userModel = new UserModel(coins,"Manpreet");
                    userDatabase.updateUser(userModel);
                    Snackbar snack = Snackbar.make(view, "The card has been purchased successfully.", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();

                }
                else{
                    Snackbar snack = Snackbar.make(view, "Insufficient coins to buy this card.", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }

            }
        });
        gameLogic=new GameLogic();
        gameLogic.setSysplayer();
        shuffledMapUserCads = gameLogic.shuffleMap();
        gameLogic.setUserplayer();
        userCardList = new ArrayList(shuffledMapUserCads.values());

        CoinsCard.setText("User Coins: " + String.valueOf(coins));
        generalCoins = coins;
        CoinsCardPrice.setText("Card Price: " + String.valueOf(coins));
        particularCoins = Integer.parseInt(userCardList.get(0).toString());

        Random r = new Random();
        int genRandom = r.nextInt(13 - 1) + 1;
        String selectedPlayer = "player" + Integer.toString(genRandom);
        Log.i("RANDOM VALUE:", Integer.toString(genRandom));
        ImageView imageFin = (ImageView)findViewById(R.id.ImageRank1);
        if(genRandom == 1) {
            imageFin.setImageResource(R.drawable.player1);
        }
        else if(genRandom == 2){
            imageFin.setImageResource(R.drawable.player2);
        }
        else if(genRandom == 3){
            imageFin.setImageResource(R.drawable.player3);
        }
        else if(genRandom == 4){
            imageFin.setImageResource(R.drawable.player4);
        }
        else if(genRandom == 5){
            imageFin.setImageResource(R.drawable.player5);
        }
        else if(genRandom == 6){
            imageFin.setImageResource(R.drawable.player6);
        }
        else if(genRandom == 7){
            imageFin.setImageResource(R.drawable.player7);
        }
        else if(genRandom == 8){
            imageFin.setImageResource(R.drawable.player8);
        }
        else if(genRandom == 9){
            imageFin.setImageResource(R.drawable.player9);
        }
        else if(genRandom == 10){
            imageFin.setImageResource(R.drawable.player10);
        }
        else if(genRandom == 11){
            imageFin.setImageResource(R.drawable.player11);
        }
        else if(genRandom == 12){
            imageFin.setImageResource(R.drawable.player12);
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String coins_val = String.valueOf(coins);
                Intent myIntent = new Intent(BuyCardActivity.this, DashboardActivity.class);
                myIntent.putExtra(pipe,coins_val);
                userDbHelper = new UserDbHelper(mActivity);
                userDatabase = new UserDatabase(mActivity);
                UserModel userModel = new UserModel(coins,"Manpreet");
                userDatabase.updateUser(userModel);
                UserModel userModel1 = userDatabase.selectUser();
                System.out.println(userModel1.getCoinsCount());

                System.out.println(userModel1.getUserName());
                startActivity(myIntent);
            }
        });

        getCardViews();
        getTextViews();
        setVisibiltyOfCards();
        gameLogic=new GameLogic();
        gameLogic.setSysplayer();
        shuffledMapUserCads = gameLogic.shuffleMap();
        gameLogic.setUserplayer();
        userCardList = new ArrayList(shuffledMapUserCads.values());

        textView1.setText("Rank "+String.valueOf(userCardList.get(0)));
        CoinsCardPrice.setText(" Card Price: " + String.valueOf(userCardList.get(0)));

        gameLogic.removeUserAssignedCardsfromSysplayer();





    }

    public void randomFunctionCall()
    {

        CoinsCard.setText("User Coins: " + String.valueOf(coins));
        generalCoins = coins;

        CoinsCardPrice.setText("Card Price: " + String.valueOf(coins));
        particularCoins = coins;

        Random r = new Random();
        int genRandom = r.nextInt(13 - 1) + 1;
        String selectedPlayer = "player" + Integer.toString(genRandom);
        Log.i("RANDOM VALUE:", Integer.toString(genRandom));
        ImageView imageFin = (ImageView)findViewById(R.id.ImageRank1);

        if(genRandom == 1) {
            imageFin.setImageResource(R.drawable.player1);
        }
        else if(genRandom == 2){
            imageFin.setImageResource(R.drawable.player2);
        }
        else if(genRandom == 3){
            imageFin.setImageResource(R.drawable.player3);
        }
        else if(genRandom == 4){
            imageFin.setImageResource(R.drawable.player4);
        }
        else if(genRandom == 5){
            imageFin.setImageResource(R.drawable.player5);
        }
        else if(genRandom == 6){
            imageFin.setImageResource(R.drawable.player6);
        }
        else if(genRandom == 7){
            imageFin.setImageResource(R.drawable.player7);
        }
        else if(genRandom == 8){
            imageFin.setImageResource(R.drawable.player8);
        }
        else if(genRandom == 9){
            imageFin.setImageResource(R.drawable.player9);
        }
        else if(genRandom == 10){
            imageFin.setImageResource(R.drawable.player10);
        }
        else if(genRandom == 11){
            imageFin.setImageResource(R.drawable.player11);
        }
        else if(genRandom == 12){
            imageFin.setImageResource(R.drawable.player12);
        }
        LinkedHashMap<Integer, Integer> shuffledMapUserCads = gameLogic.shuffleMap();
        gameLogic.setUserplayer();
        List userCardList = new ArrayList(shuffledMapUserCads.values());

        textView1.setText("Rank "+String.valueOf(userCardList.get(0)));
        CoinsCardPrice.setText(" Card Price: " + String.valueOf(userCardList.get(0)));
        particularCoins = Integer.parseInt(String.valueOf(userCardList.get(0)));


        gameLogic.removeUserAssignedCardsfromSysplayer();



    }
    /**
     * This method will be used to set the
     * visibility of cards depending upon whether it is
     * a user's card or system's card
     */
    public void setVisibiltyOfCards(){

    }

    /**
     * This method will fetch
     * the card view objects
     */
    public void getCardViews(){
        cardView1 = findViewById(R.id.card_view_1);

    }

    /**
     * This method will fetch
     * the card view objects
     */
    public void getTextViews(){
        textView1 = findViewById(R.id.Rank1);

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(BuyCardActivity.this, DashboardActivity.class);
        userDbHelper = new UserDbHelper(this);
        userDatabase = new UserDatabase(mActivity);
        UserModel userModel = new UserModel(coins,"Manpreet");
        userDatabase.updateUser(userModel);
        startActivity(myIntent);
    }

}
