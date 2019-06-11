package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDelete {


    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public EmployeeDelete() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Delete(Empoyee employee) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "DELETE FROM EMPLOYEE WHERE ID_Employee = " + employee.getID_Employee();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }
}
