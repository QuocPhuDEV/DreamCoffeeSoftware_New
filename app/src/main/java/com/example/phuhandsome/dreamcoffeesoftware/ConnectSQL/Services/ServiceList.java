package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Services;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.Sale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceList {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public ServiceList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Services> getServicesList() throws SQLException {
        List<Services> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM SERVICES";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Services(rs.getInt("ID_Services"), rs.getString("Servicename"), rs.getString("Describe")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
