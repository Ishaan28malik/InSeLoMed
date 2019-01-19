package com.example.cypher.projinselo.model;

import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;

import com.example.cypher.projinselo.ScrollingActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class use_pres extends AsyncTask<Void, Void, Void> {

    public String dburl, user, password;
    public static Connection Conn;


    public static String uses;
    public static String uses_get;
    public static String pres;
    public static String med_co;
    public static String pres_get;
    public static String side_eff = "";
    public static String side_get;

    //Constructor without parameters
    public use_pres() {
        Log.d("Print Log", "Constructor()");
    }

    //Constructor with parameters
    public use_pres(String m) {
        med_co = m;
        Log.d("Print Log", "Constructor()");
    }


    public String get_uses(String med_code) {
        String query = "";

        try {
            query = "SELECT Uses FROM medicine WHERE Medicine_Code =?";
            PreparedStatement statement = Conn.prepareStatement(query);
            statement.setString(1, med_code);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                uses = rs.getString("Uses");

            }
        } catch (Exception e) {
            System.out.println("Error in GET USES::::::::" + e);
        }
        System.out.println("Uses::::::::" + uses);
        return uses;
    }

    public String prescription(String med_code) {
        String query = "";
        try {
            query = "SELECT Prescription FROM medicine WHERE Medicine_Code =?";
            PreparedStatement statement = Conn.prepareStatement(query);
            statement.setString(1, med_code);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                pres = rs.getString("Prescription");
            }
        } catch (Exception e) {
            System.out.println("Error in Prescription::::::::" + e);
        }
        System.out.println("Prescripiton::::" + pres);

        return pres;
    }

    public String side_effect(String med_code) {
        String query = "";
        String query2 = "";
        String side_code = "", side_name = "";
        String[] array;
        try {
            query = "SELECT Side_Effects FROM medicine WHERE Medicine_Code =?";
            PreparedStatement statement = Conn.prepareStatement(query);
            statement.setString(1, med_code);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                side_code = rs.getString("Side_Effects");
            }
            array = side_code.split(",");

            for (int i = 0; i < array.length; i++) {
                query2 = "SELECT Side_Effect_Name FROM sideeffects WHERE Side_Effect_Code=?";
                PreparedStatement statement2 = Conn.prepareStatement(query2);
                statement2.setString(1, array[i]);

                ResultSet rs2 = statement2.executeQuery();

                if (rs2.next()) {
                    side_name = rs2.getString("Side_Effect_Name");
                    side_eff += side_name + ", ";

                }

            }

        } catch (Exception e) {
            System.out.println("Error in Side_Effects::::::::" + e);
        }
        return side_eff;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        dburl = "jdbc:mysql://sql12.freemysqlhosting.net/sql12273219";
        user = "sql12273219";
        password = "QgXYMTZXzl";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Before Class.forname");
            Conn = DriverManager.getConnection(dburl, user, password);
            System.out.println("Connection Successful to Database: " + dburl);

        } catch (Exception e) {
            System.out.println("ERROR IN DO in backgrounnd :- " + e);
        }
        uses_get = get_uses(med_co);
        pres_get = prescription(med_co);
        side_get = side_effect(med_co);

        return null;
    }

    protected void onPostExecute(Void result) {
        ScrollingActivity.pres_text.setText(pres_get);
        ScrollingActivity.use_text.setText(uses_get);
        ScrollingActivity.side_text.setText(side_get);
    }
}
