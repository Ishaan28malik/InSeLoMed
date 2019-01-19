package com.example.cypher.projinselo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;


/**
 * Created by cypher on 31/03/18.
 */

public class anotherbrowser extends AppCompatActivity{

    private WebView web;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybrowser);
        web=(WebView)findViewById(R.id.webView);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.loadUrl("https://www.youtube.com/watch?v=T3kwv08D6KA");
    }

}
