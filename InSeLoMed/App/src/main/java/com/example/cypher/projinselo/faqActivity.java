package com.example.cypher.projinselo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class faqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
    }
    public void web_one (View view)
    {
        Intent intent = new Intent(this,mybrowser.class);

        startActivity(intent);
    }

    public void web_two (View view)
    {
        Intent intent = new Intent(this,anotherbrowser.class);

        startActivity(intent);
    }
}
