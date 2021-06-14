package cm.btech2021.houser.activities.game;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cm.btech2021.houser.R;

public class TicTacToeBoard extends View {
    final int boardColor;
    final int xColor;
    final int oColor;
    final int winingLineColor;

    boolean winingLine = false;

    final Paint paint = new Paint();

    final TicTacToeGameLogic game;

    int cellSize = getWidth() / 3;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new TicTacToeGameLogic();

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToeBoard, 0, 0);

        try {
            boardColor = array.getInteger(R.styleable.TicTacToeBoard_boardColor, 0);
            xColor = array.getInteger(R.styleable.TicTacToeBoard_xColor, 0);
            oColor = array.getInteger(R.styleable.TicTacToeBoard_oColor, 0);
            winingLineColor = array.getInteger(R.styleable.TicTacToeBoard_winingLineColor, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y / cellSize);
            int column = (int) Math.ceil(x / cellSize);

            if (!winingLine) {
                if (game.updateGameBoard(row, column)) {
                    invalidate();

                    if (game.winnerCheck()){
                        winingLine = true;
                        invalidate();
                    }

                    // Updating player's turn
                    if (game.getPlayer() % 2 == 0){
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }

            return true;
        }
        return false;
    }

    void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int c = 1; c < 3; c++) {
            canvas.drawLine(cellSize * c, 0, cellSize * c, canvas.getWidth(), paint);
        }

        for (int r = 1; r < 3; r++) {
            canvas.drawLine(0, cellSize * r, canvas.getWidth(), cellSize * r, paint);
        }
    }

    void drawMarkers(Canvas canvas){
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (game.getGameBoard()[r][c] != 0) {
                    if (game.getGameBoard()[r][c] == 1){
                        drawX(canvas, r, c);
                    } else {
                        drawO(canvas, r, c);
                    }
                }
            }
        }
    }

    void drawX(Canvas canvas, int row, int column){
        paint.setColor(xColor);
        canvas.drawLine(
                (float) ((column + 1) * cellSize - (cellSize * 0.2)),
                (float) ((row * cellSize) + (cellSize * 0.2)),
                (float) ((column * cellSize) + (cellSize * 0.2)),
                (float) (((row + 1) * cellSize) - (cellSize * 0.2)),
                paint
        );
        canvas.drawLine(
                (float) ((column * cellSize) + (cellSize * 0.2)),
                (float) ((row * cellSize) + (cellSize * 0.2)),
                (float) (((column + 1) * cellSize) - (cellSize * 0.2)),
                (float) (((row + 1) * cellSize) - (cellSize * 0.2)),
                paint
        );
    }

    void drawO(Canvas canvas, int row, int column){
        paint.setColor(oColor);
        canvas.drawOval(
                (float) (column * cellSize + (cellSize * 0.2)),
                (float) (row * cellSize + (cellSize * 0.2)),
                (float) ((column * cellSize) + cellSize - (cellSize * 0.2)),
                (float) ((row * cellSize) + cellSize - (cellSize * 0.2)),
                paint
        );
    }

    void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names) {
        game.setPlayAgainBtn(playAgain);
        game.setHomeBtn(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);
    }

    void resetGame(){
        game.resetGame();
        winingLine = false;
    }
}
