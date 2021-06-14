package cm.btech2021.houser.activities.game;

import static cm.btech2021.houser.activities.game.TicTacToeSetupActivity.EXTRA_PLAYER_NAMES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cm.btech2021.houser.R;

public class TicTacToeGameActivity extends AppCompatActivity {
    TicTacToeBoard ticTacToeBoard;
    Button playAgainBtn;
    Button homeBtn;
    TextView playerTurn;
    String[] playerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);

        playAgainBtn = findViewById(R.id.gameTryAgainBtn);
        homeBtn = findViewById(R.id.gameHomeBtn);
        playerTurn = findViewById(R.id.gameCurrentPlayer);
        playerNames = getIntent().getStringArrayExtra(EXTRA_PLAYER_NAMES);

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);


        if (playerNames != null){
            playerTurn.setText(playerNames[0] + "'s turn");
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgainBtn, homeBtn, playerTurn, playerNames);
    }

    public void playAgainButtonClick(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButtonClick(View view){
        startActivity(new Intent(this, TicTacToeHomeActivity.class));
    }
}