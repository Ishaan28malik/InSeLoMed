package com.example.cypher.projinselo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    public static TextView med_details3;
    public static TextView alt_details3;
    public static TextView salt_details3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        med_details3=findViewById(R.id.editText);
        alt_details3=findViewById(R.id.alt);
        salt_details3=findViewById(R.id.salt);

        alter_database alter_database = new alter_database();
        alter_database.execute();

    }

}
