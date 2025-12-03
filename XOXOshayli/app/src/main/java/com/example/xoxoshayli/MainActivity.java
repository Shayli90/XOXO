package com.example.xoxoshayli;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private modelXO game;
    private String player1;
    private String player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        game = new modelXO();
        Intent i = getIntent();
        player1 = i.getStringExtra("Player1");
        player2 = i.getStringExtra("Player2");
        TextView view = findViewById(R.id.textView);
        String text = player1 + " Turn";
        view.setText(text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boolean game = true;
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run(){
                //super.run();
                int counter = 0;
                Log.d("msg","TimerThread started.");
                while (game) {
                    counter++;
                    SystemClock.sleep(1000);
                    Log.d("TIMER", "run: i = " + counter);

                    int finalCounter = counter;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = findViewById(R.id.textView5);
                            textView.setText("Timer: " + finalCounter);
                        }
                    });

                }
                Log.d("msg", "TimerThread finished.");
            }
        });
        timer.start();

    }

    public void move(View view) {
        String tag = (String)view.getTag();
        String winner;
        int row = tag.charAt(0) - '0';
        int col = tag.charAt(1) - '0';
        if (!game.isEmpty(row,col) || game.checkWin())
            return;

        int player = game.getCurrentPlayer();
        game.makeMove(row,col);

        TextView title = findViewById(R.id.textView);
        Button button = findViewById(view.getId());
        if (player == 1) {
            button.setText("X");
            String text = player2 + " Turn";
            title.setText(text);
            winner = player1;
        }
        else {
            button.setText("O");
            String text = player1 + " Turn";
            title.setText(text);
            winner = player2;
        }
        if (game.checkWin())
        {
            Intent intent = new Intent(this, endScreen_winner.class);
            intent.putExtra("winner", winner);
            startActivity(intent);
        }
        else if (game.checkTie())
        {
            Intent intent = new Intent(this, endScreen_tie.class);
            startActivity(intent);
        }
    }
}