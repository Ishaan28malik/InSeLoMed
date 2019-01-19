package com.example.cypher.projinselo;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.cypher.projinselo.model.DB_connection;

import java.sql.*;

/**
 * Created by Anmol on 3/27/2018.
 */

public class alter_database extends AsyncTask<Void,Void,Void> {

    public String dburl,user,password;
    public static Connection Conn;


    public static String med_name;
    public static String state;
    public static String salt;
    public static String med_code;
    public static String comp;
  //  public ImageView barcode;
    public static String alternative;

    alter_database(){};

    alter_database(String med_name, String state, String salt)// ImageView img)
    {
        this.med_name = med_name;
        this.state = state;
        this.salt = salt;
       // this.barcode=img;

        DB_connection dbConnection=new DB_connection();
        this.med_code=dbConnection.getMedCode(this.med_name);
        this.comp=dbConnection.getComposition(med_code);

        System.out.println("MED-NAME:::"+med_name);
        System.out.println("STATE   :::"+state);
        System.out.println("SALT    :::"+salt);

    }
    static String getAlternateMedicine(String Comp, String State)
    {
        String AltMed = "";
        String Mcode = "";
        String Mcodes = "";
        String query1;
        String query2;
        String query3;


        try
        {   query1="SELECT Medicine_Codes FROM states WHERE State_Name = ?";
            PreparedStatement statementState=Conn.prepareStatement(query1);
            statementState.setString(1,State);

            ResultSet rsState=statementState.executeQuery();

            rsState.next();
            Mcodes=rsState.getString(1);

            query2="SELECT Medicine_Code FROM composition WHERE Comp = ?";
            PreparedStatement statement=Conn.prepareStatement(query2);
            statement.setString(1,Comp);

            ResultSet rs=statement.executeQuery();

            while(rs.next())
            {
                Mcode=rs.getString(1);
                if(Mcodes.contains(Mcode))
                {
                    query3="SELECT Common_Name FROM medicine WHERE Medicine_Code = ?";
                    PreparedStatement statementMed=Conn.prepareStatement(query3);
                    statementMed.setString(1,Mcode);

                    ResultSet rsMed=statementMed.executeQuery();

                    rsMed.next();
                    AltMed+=rsMed.getString(1);

                    if(!rs.isLast())
                    {
                        AltMed+=",";
                    }
                }


            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR in ALternative MEdicies :::"+e);
        }
        System.out.println("ALternativee medicines::::"+AltMed);
        return AltMed;
    }


    @Override
    protected Void doInBackground(Void... voids)
    {
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
        alternative=getAlternateMedicine(comp,state);

        return null;
    }
    protected void onPostExecute(Void result) {
        Activity3.med_details3.setText(med_name);
        Activity3.salt_details3.setText(salt);
        Activity3.alt_details3.setText(alternative);
    }

}
