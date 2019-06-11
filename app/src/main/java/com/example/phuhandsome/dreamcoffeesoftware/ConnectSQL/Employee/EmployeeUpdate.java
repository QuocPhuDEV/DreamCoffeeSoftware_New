package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeUpdate {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public EmployeeUpdate() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Update(Empoyee employee) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "UPDATE EMPLOYEE SET Employeename = " + "N'" + employee.getEmployeename() + "'"
                + ",Birthofdate = " + "'" + employee.getBirthofdate() + "'"
                + ",sex = " + "'" + employee.getSex() + "'"
                + ",Phonenumber = " + "'" + employee.getPhonenumber() + "'"
                + ",Email = " + "'" + employee.getEmail() + "'"
                + ",CusAddress = " + "'" + employee.getCusAddress() + "'"
                + " WHERE ID_Employee = " + employee.getID_Employee();
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }
}
