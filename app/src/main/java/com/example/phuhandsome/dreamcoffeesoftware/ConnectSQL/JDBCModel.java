package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCModel {
    public Connection getConnectionOf() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection objConn = null;
        String sConnURL = null;
        JDBCObject objEntity = new JDBCObject("coffeemanager.mssql.somee.com", "phuhq95_SQLLogin_1", "n4xm4tku1a", "coffeemanager", "1433");
        try {
            Class.forName(objEntity.getsClass());
            sConnURL = "jdbc:jtds:sqlserver://"
                    + objEntity.getsServerName() + ":" + objEntity.getsPrort() + ";"
                    + "databaseName=" + objEntity.getsDatabases()
                    + ";user=" + objEntity.getsUserId()
                    + ";password=" + objEntity.getsPwd() + ";";
            objConn = DriverManager.getConnection(sConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return objConn;
    }
}
