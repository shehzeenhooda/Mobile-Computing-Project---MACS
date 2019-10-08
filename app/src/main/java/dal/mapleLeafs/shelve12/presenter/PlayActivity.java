package dal.mapleLeafs.shelve12.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import androidx.cardview.widget.CardView;
import dal.mapleLeafs.shelve12.model.GameLogic;
import dal.mapleLeafs.shelve12.R;
import dal.mapleLeafs.shelve12.database.UserDatabase;
import dal.mapleLeafs.shelve12.database.UserDbHelper;
import dal.mapleLeafs.shelve12.model.UserModel;

import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private TextView textView;
    private final int vibrateWinMs = 500;
    private Vibrator hapticFeedback;
    private static CardView cardView1;
    private static CardView cardView2;
    private static CardView cardView3;
    private static CardView cardView4;
    private static CardView cardView5;
    private static CardView cardView6;
    private static CardView cardView7;
    private static CardView cardView8;
    private static CardView cardView9;
    private static CardView cardView10;
    private static CardView cardView11;
    private static CardView cardView12;


    private static TextView textView1;
    private static TextView textView2;
    private static TextView textView3;
    private static TextView textView4;
    private static TextView textView5;
    private static TextView textView6;
    private static TextView textView7;
    private static TextView textView8;
    private static TextView textView9;
    private static TextView textView10;
    private static TextView textView11;
    private static TextView textView12;

    private boolean cardOneClicked = false;
    private boolean cardTwoClicked = false;
    private boolean cardThreeClicked = false;
    private boolean cardFourClicked = false;
    private boolean cardFiveClicked = false;
    private boolean cardSixClicked = false;

    private int coins;
    public final static String pipe ="coins_val";

    private UserDatabase userDatabase;
    private GameLogic gameLogic;
    private UserDbHelper userDbHelper;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mActivity=this;
        userDbHelper = new UserDbHelper(this);
        userDatabase = new UserDatabase(mActivity);
        UserModel userModel = userDatabase.selectUser();
        coins = userModel.getCoinsCount();

        hapticFeedback = (Vibrator) getSystemService (Context.VIBRATOR_SERVICE);
        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String coins_val = String.valueOf(coins);
                Intent myIntent = new Intent(PlayActivity.this, DashboardActivity.class);
                myIntent.putExtra(pipe,coins_val);
                // Start for Bug Fix: Inserting the coins into database
                userDbHelper = new UserDbHelper(mActivity);
                userDatabase = new UserDatabase(mActivity);
                // End for Bug Fix: Inserting the coins into database
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
        LinkedHashMap<Integer, Integer> shuffledMapUserCads = gameLogic.shuffleMap();
        gameLogic.setUserplayer();
        List userCardList = new ArrayList(shuffledMapUserCads.values());

        textView7.setText("Rank "+String.valueOf(userCardList.get(0)));
        textView8.setText("Rank "+String.valueOf(userCardList.get(1)));
        textView9.setText("Rank "+String.valueOf(userCardList.get(2)));
        textView10.setText("Rank "+String.valueOf(userCardList.get(3)));
        textView11.setText("Rank "+String.valueOf(userCardList.get(4)));
        textView12.setText("Rank "+String.valueOf(userCardList.get(5)));

        gameLogic.removeUserAssignedCardsfromSysplayer();
        LinkedHashMap<Integer, Integer> shuffledMapSystemCards = gameLogic.getFirstSixGlobalShuffledCards();
        List systemCardList = new ArrayList(shuffledMapSystemCards.values());
        textView1.setText("Rank "+String.valueOf(systemCardList.get(0)));
        textView2.setText("Rank "+String.valueOf(systemCardList.get(1)));
        textView3.setText("Rank "+String.valueOf(systemCardList.get(2)));
        textView4.setText("Rank "+String.valueOf(systemCardList.get(3)));
        textView5.setText("Rank "+String.valueOf(systemCardList.get(4)));
        textView6.setText("Rank "+String.valueOf(systemCardList.get(5)));

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardOneClicked) {
                    cardOneClicked=true;
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView1.setVisibility(View.VISIBLE);
                    String val1 = textView7.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView1.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView1.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView7.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }
                else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }
        });

        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardTwoClicked) {
                    cardTwoClicked=true;
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView2.setVisibility(View.VISIBLE);
                    String val1 = textView8.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView2.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView2.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView8.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }
        });

        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardThreeClicked) {
                    cardThreeClicked = true;
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView3.setVisibility(View.VISIBLE);
                    String val1 = textView9.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView3.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView3.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView9.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }
                else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }
        });

        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardFourClicked) {
                    cardFourClicked=true;
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView4.setVisibility(View.VISIBLE);
                    String val1 = textView10.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView4.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView4.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView10.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }
                else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }

        });

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardFiveClicked) {
                    cardFiveClicked=true;
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView5.setVisibility(View.VISIBLE);
                    String val1 = textView7.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView1.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView5.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView11.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }
                else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }
        });

        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cardSixClicked) {
                    hapticFeedback.vibrate(vibrateWinMs);
                    cardView6.setVisibility(View.VISIBLE);
                    String val1 = textView7.getText().toString();
                    int i = Integer.parseInt(val1.substring(5));
                    String val2 = textView1.getText().toString();
                    int j = Integer.parseInt(val2.substring(5));
                    SystemClock.sleep(300);
                    if (i > j) {
                        cardView6.setVisibility(View.INVISIBLE);
                        coins += 10;
                        Snackbar snack = Snackbar.make(v, "User Wins the Card! +10 Coins",
                                Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    } else {
                        cardView12.setVisibility(View.INVISIBLE);
                        Snackbar snack = Snackbar.make(v, "System Wins the Card", Snackbar.LENGTH_LONG).setAction("Action", null);
                        snack.show();
                    }
                }else{
                    Snackbar snack = Snackbar.make(v, "Card has already been exchanged", Snackbar.LENGTH_LONG).setAction("Action", null);
                    snack.show();
                }
            }
        });

    }

    /**
     * This method will be used to set the
     * visibility of cards depending upon whether it is
     * a user's card or system's card
     */
    public void setVisibiltyOfCards(){
        cardView1.setVisibility(View.INVISIBLE);
        cardView2.setVisibility(View.INVISIBLE);
        cardView3.setVisibility(View.INVISIBLE);
        cardView4.setVisibility(View.INVISIBLE);
        cardView5.setVisibility(View.INVISIBLE);
        cardView6.setVisibility(View.INVISIBLE);
    }

    /**
     * This method will fetch
     * the card view objects
     */
    public void getCardViews(){
        cardView1 = findViewById(R.id.card_view_1);
        cardView2 = findViewById(R.id.card_view_2);
        cardView3 = findViewById(R.id.card_view_3);
        cardView4 = findViewById(R.id.card_view_4);
        cardView5 = findViewById(R.id.card_view_5);
        cardView6 = findViewById(R.id.card_view_6);

        cardView7 = findViewById(R.id.card_view_7);
        cardView8 = findViewById(R.id.card_view_8);
        cardView9 = findViewById(R.id.card_view_9);
        cardView10 = findViewById(R.id.card_view_10);
        cardView11 = findViewById(R.id.card_view_11);
        cardView12 = findViewById(R.id.card_view_12);
    }

    /**
     * This method will fetch
     * the card view objects
     */
    public void getTextViews(){
        textView1 = findViewById(R.id.Rank1);
        textView2 = findViewById(R.id.Rank2);
        textView3 = findViewById(R.id.Rank3);
        textView4 = findViewById(R.id.Rank4);
        textView5 = findViewById(R.id.Rank5);
        textView6 = findViewById(R.id.Rank6);

        textView7 = findViewById(R.id.Rank7);
        textView8 = findViewById(R.id.Rank8);
        textView9 = findViewById(R.id.Rank9);
        textView10 = findViewById(R.id.Rank10);
        textView11 = findViewById(R.id.Rank11);
        textView12 = findViewById(R.id.Rank12);

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(PlayActivity.this, DashboardActivity.class);
        userDbHelper = new UserDbHelper(this);
        userDatabase = new UserDatabase(mActivity);
        UserModel userModel = new UserModel(coins,"Manpreet");
        userDatabase.updateUser(userModel);
        startActivity(myIntent);
    }

}
