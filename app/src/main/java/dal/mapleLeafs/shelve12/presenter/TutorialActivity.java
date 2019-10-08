package dal.mapleLeafs.shelve12.presenter;

import androidx.appcompat.app.AppCompatActivity;
import dal.mapleLeafs.shelve12.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TutorialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        TextView tutorial = findViewById(R.id.tutorial);
        tutorial.setText("The game implements its playtime by providing you with a few NHL cards.These cards will then be competed against the system for comparison based on the sport ranking of each player in NHL team. During a typical competition, either you will win the card from system or loose to it. Whenever you run out of cards, you can reset the game and again start fresh. Furthermore, you can compete with other players also and win card from them, along with 10 virtual coins and with every 150 coins user will earn a medal as a scale up.Once you complete an 11-member team, you will be levelled up and correspondingly get 20 accompanying coins with every card won from another player.");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TutorialActivity.this, DashboardActivity.class);
                startActivity(myIntent);

            }
        });
    }
}
