package com.example.cypher.projinselo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.cypher.projinselo.model.use_pres;

public class ScrollingActivity extends AppCompatActivity {

    public static TextView pres_text;
    public static TextView use_text;
    public static TextView side_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Slide up..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        pres_text = findViewById(R.id.pres_text);
        use_text = findViewById(R.id.use_text);
        side_text = findViewById(R.id.side_text);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
