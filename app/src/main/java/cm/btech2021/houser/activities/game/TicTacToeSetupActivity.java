package cm.btech2021.houser.activities.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cm.btech2021.houser.R;

public class TicTacToeSetupActivity extends AppCompatActivity {
    public static final String EXTRA_PLAYER_NAMES = "EXTRA_PLAYER_NAMES";
    EditText txtFirstPlayerName;
    EditText txtSecondPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_setup);

        txtFirstPlayerName = findViewById(R.id.gameSetupTxtFirstPlayerName);
        txtSecondPlayerName = findViewById(R.id.gameSetupTxtSecondPlayerName);
    }

    public void submitButtonClick(View view){
        String firstPlayerName = txtFirstPlayerName.getText().toString();
        String secondPlayerName = txtSecondPlayerName.getText().toString();

        Intent intent = new Intent(this, TicTacToeGameActivity.class);
        intent.putExtra(EXTRA_PLAYER_NAMES, new String[] {firstPlayerName, secondPlayerName});
        startActivity(intent);
    }
}