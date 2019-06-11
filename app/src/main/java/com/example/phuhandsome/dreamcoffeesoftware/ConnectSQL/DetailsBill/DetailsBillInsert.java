package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsBillInsert {


    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public DetailsBillInsert() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Add(DetailsBill detailsBill) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "INSERT INTO DETAILSBILL VALUES (" + "' '"
                + ",' '"
                + "," + detailsBill.getNumTable()
                + ",N'" + detailsBill.getDrinksName() + "'"
                + "," + detailsBill.getNumberOrder()
                + ",0"
                + ",0"
                + ",' '"
                + ",0"
                + ",0"
                + ",0"
                + ",0" + ")";
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }

}
