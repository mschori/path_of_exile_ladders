package com.example.m335_poe_ladders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create intent
        this.intent = new Intent(MainActivity.this, LadderActivity.class);

        // Get views
        TextView metamorphStandard = (TextView) findViewById(R.id.metamorph_standard);
        ImageView metamorphStandardArrow = (ImageView) findViewById(R.id.metamorph_standard_arrow);
        TextView metamorphHardcore = (TextView) findViewById(R.id.metamorph_hardcore);
        ImageView metamorphHardcoreArrow = (ImageView) findViewById(R.id.metamorph_hardcore_arrow);

        // Set a click listener on metamorph standard
        metamorphStandard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Bind extra to intent and start intent
                intent.putExtra("league", "metamorph_standard");
                startActivity(intent);
            }
        });
        metamorphStandardArrow.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Bind extra to intent and start intent
                intent.putExtra("league", "metamorph_standard");
                startActivity(intent);
            }
        });

        // Set a click listener on metamorph hardcore
        metamorphHardcore.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Bind extra to intent and start intent
                intent.putExtra("league", "metamorph_hardcore");
                startActivity(intent);
            }
        });
        metamorphHardcoreArrow.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Bind extra to intent and start intent
                intent.putExtra("league", "metamorph_hardcore");
                startActivity(intent);
            }
        });
    }
}
