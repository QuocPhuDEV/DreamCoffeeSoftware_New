package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public EmployeeList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Empoyee> getEmployeeList() throws SQLException {
        List<Empoyee> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM EMPLOYEE";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Empoyee(rs.getInt("ID_Employee"), rs.getInt("ID_Account"), rs.getInt("ID_Position"), rs.getString("Employeename"), rs.getString("Birthofdate"), rs.getString("sex"), rs.getString("Phonenumber"), rs.getString("Email"), rs.getString("CusAddress")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
