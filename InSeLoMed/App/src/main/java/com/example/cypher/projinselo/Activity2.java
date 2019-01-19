package com.example.cypher.projinselo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cypher.projinselo.model.DB_connection;
import com.example.cypher.projinselo.model.use_pres;

public class Activity2 extends AppCompatActivity {

    public Context context = this;
    public static TextView med_details;
    public static TextView salt_details;
    public static AutoCompleteTextView state;
    public static String state_name;
    public static String med_name;
    public String med_code;
    public static String salt;
    ArrayAdapter<String> adapter;
    public Button button21;
    public Button conflictMed;
    public String[]
            stat_nm = {"Delhi", "Mumbai", "Kolkata", "Gujrat", "Chennai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        med_details = findViewById(R.id.editText);
        salt_details = findViewById(R.id.salt_detail);
        state = (AutoCompleteTextView) findViewById(R.id.stateName);
        button21 = (Button) findViewById(R.id.button2_1);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stat_nm);
        state.setAdapter(adapter);

        state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                Toast.makeText(Activity2.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        conflictMed = (Button) findViewById(R.id.btnConflictMed);

        conflictMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func();
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.popup_location, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        map(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        map();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    private void func() {
        Intent intent = new Intent(this, ConflictActivity.class);
        startActivity(intent);
    }


    public void onlocate(View view) {
        state_name = state.getText().toString();
        med_name = med_details.getText().toString();
        salt = salt_details.getText().toString();

        alter_database alterDatabase = new alter_database(med_name, state_name, salt);

        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    public void map(String location) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + location + "+pharmacy");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void map() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=pharmacy");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void info(View view) {
        DB_connection db = new DB_connection();
        med_code = db.getMedCode(med_name);
        new use_pres(med_code).execute();
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }


}
