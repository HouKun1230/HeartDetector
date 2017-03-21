package com.example.android.wearablemessageapiexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.android.wearablemessageapiexample.MainActivity.pass;
import static com.example.android.wearablemessageapiexample.MainActivity.user;

/**
 * Created by kun on 2017-03-15.
 */

public class Update extends AsyncTask<String, String, String> {

    ProgressDialog mProgressDialog;
    Context context;
    private String url;
    private String date;
    private String Value_rate;
    java.sql.PreparedStatement preparedStatement = null;


    public Update(Context context, String date, String url, String value) {
        this.context = context;
        this.url = url;
        this.date = date;
        this.Value_rate = value;
    }



    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Please wait, getting database...");
    }

    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            encrypt en = new encrypt();
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            java.sql.Statement st = con.createStatement();

          //  String sql = "INSERT INTO UserHeart (username, heartrate,Date) VALUES ( kun, "  + Value_rate + "," + date + ")";

            //String sql = "update Lamp set Status='ON' where RoomId=4005";
           // int rs = st.executeUpdate(sql);
            String insertTableSQL = "INSERT INTO UserHeart"
                    + "(userId,heartrate,Date) VALUES"
                    + "(?,?,?)";
            preparedStatement = con.prepareStatement(insertTableSQL);


            preparedStatement.setString(1, "0528316");
            preparedStatement.setString(2, Value_rate);
            preparedStatement.setString(3, date);
            preparedStatement.executeUpdate();



//            while (rs.next()) {
////                    String field= rs.getString("field");
////                    MainActivity.playerList.add(new String(field));
//                lamp  = rs.getString("Value");
//
//            }
            st.close();
            con.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "Complete";
    }

    protected void onPostExecute(String result) {
        if (result.equals("Complete")) {
            mProgressDialog.dismiss();
          //  myHandler.sendEmptyMessage(0);
        }
    }


}
