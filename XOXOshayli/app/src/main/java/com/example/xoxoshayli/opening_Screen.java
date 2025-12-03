package com.example.xoxoshayli;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class opening_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opening_screen_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void continueToGame(View view) {
        EditText player1 = findViewById(R.id.editTextText);
        EditText player2 = findViewById(R.id.editTextText2);

        if (TextUtils.isEmpty(player1.getText()) || TextUtils.isEmpty(player2.getText()))
        {
            Toast.makeText(this, "Enter both players", Toast.LENGTH_SHORT).show();
            return;
        }

        String name1 = player1.getText().toString();
        String name2 = player2.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Player1", name1);
        intent.putExtra("Player2", name2);
        startActivity(intent);

    }
}