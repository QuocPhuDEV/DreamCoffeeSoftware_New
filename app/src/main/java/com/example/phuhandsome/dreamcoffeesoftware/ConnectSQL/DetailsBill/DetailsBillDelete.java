package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsBillDelete {


    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public DetailsBillDelete() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Delete(DetailsBill detailsBill) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "DELETE FROM DETAILSBILL WHERE Numtable = " + detailsBill.getNumTable();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }

    public boolean DeleteRequest(Request request) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "DELETE FROM REQUEST WHERE Numtable = " + request.getNumtable();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }
}
