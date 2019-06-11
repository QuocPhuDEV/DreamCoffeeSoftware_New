package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleList {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public SaleList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Sale> getSaleList() throws SQLException {
        List<Sale> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM SALE";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Sale(rs.getInt("ID_Sale"), rs.getString("Datesale"), rs.getInt("PercentSale")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
