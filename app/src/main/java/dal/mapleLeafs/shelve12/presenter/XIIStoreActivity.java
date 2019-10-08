package dal.mapleLeafs.shelve12.presenter;

import androidx.appcompat.app.AppCompatActivity;
import dal.mapleLeafs.shelve12.R;
import dal.mapleLeafs.shelve12.database.UserDatabase;
import dal.mapleLeafs.shelve12.database.UserDbHelper;
import dal.mapleLeafs.shelve12.model.UserModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class XIIStoreActivity extends AppCompatActivity {

    TextView usersCoins;
    private Activity activity;

    UserDbHelper userDbHelper;
    private UserDatabase userDatabase;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiistore);

        activity = this;
        usersCoins = findViewById(R.id.usersCoins);
        userDbHelper = new UserDbHelper(this);
        userDatabase = new UserDatabase(activity);
        buyButton = findViewById(R.id.buyButton);
        UserModel userModel = userDatabase.selectUser();
        usersCoins.setText(String.valueOf(userModel.getCoinsCount()));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent myIntent = new Intent(XIIStoreActivity.this, BuyCardActivity.class);
                 startActivity(myIntent);
//            ///coder shehzeen
              //  Toast.makeText(getApplicationContext(), "clicked normally", Toast.LENGTH_SHORT).show();

            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(XIIStoreActivity.this, DashboardActivity.class);
                startActivity(myIntent);

            }
        });
    }
}
