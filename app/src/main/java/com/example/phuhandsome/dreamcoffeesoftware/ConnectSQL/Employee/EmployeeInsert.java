package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeInsert {


    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public EmployeeInsert() {
        connection = jdbcController.ConnecttionData();
    }

    public boolean Add(Empoyee employee) throws SQLException {
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "INSERT INTO EMPLOYEE VALUES ("+ employee.getID_Employee()
                + "," + employee.getID_Account()
                + "," + employee.getID_Position()
                + ",N'" + employee.getEmployeename() + "'"
                + ",'" + employee.getBirthofdate() + "'"
                + ",'" + employee.getSex() + "'"
                + ",'" + employee.getPhonenumber() + "'"
                + ",'" + employee.getEmail() + "'"
                + ",N'" + employee.getCusAddress() + "')";
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else
            connection.close();
        return false;
    }
}
