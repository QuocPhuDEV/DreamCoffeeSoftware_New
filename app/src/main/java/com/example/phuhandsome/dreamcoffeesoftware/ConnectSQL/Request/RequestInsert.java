package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBill;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestInsert {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public RequestInsert() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Add(Request request) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "INSERT INTO REQUEST VALUES (" + request.getNumtable()
                + ",'" + request.getRequest() + "'"
                + ")";
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }

}
