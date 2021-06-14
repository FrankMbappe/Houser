package cm.btech2021.houser.activities.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cm.btech2021.houser.R;
import cm.btech2021.houser.activities.MainActivity;

public class TicTacToeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_home);
    }

    public void playButtonClick(View view){
        startActivity(new Intent(this, TicTacToeSetupActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}