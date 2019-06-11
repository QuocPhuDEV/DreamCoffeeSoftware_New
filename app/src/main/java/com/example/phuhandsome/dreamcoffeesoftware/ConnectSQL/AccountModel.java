package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountModel {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public AccountModel() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Account> getAccountList() throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM ACCOUNT";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Account(rs.getInt("ID_Account"), rs.getString("Username"), rs.getString("Passwords"), rs.getString("Permission")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
