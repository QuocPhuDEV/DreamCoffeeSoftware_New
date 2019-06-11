package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.LoginFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Customer.Customer;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerLoginWithUser {


    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public CustomerLoginWithUser() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Customer> getCustomerList() throws SQLException {
        List<Customer> list = new ArrayList<>();
        LoginFragment loginFragment = new LoginFragment();
        String idAccount = loginFragment.idAccountLogin;
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM CUSTOMER WHERE ID_Account = '"+idAccount+"' ";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Customer(rs.getInt("ID_Customer"), rs.getInt("ID_Account"), rs.getString("Customername"), rs.getString("Birthofdate"), rs.getString("sex"), rs.getString("Phonenumber"), rs.getString("Email"), rs.getString("CusAddress"), rs.getInt("C_coint")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
