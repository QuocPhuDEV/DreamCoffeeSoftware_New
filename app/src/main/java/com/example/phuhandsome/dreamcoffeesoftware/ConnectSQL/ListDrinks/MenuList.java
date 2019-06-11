package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuList {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public MenuList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Menu> getMenuList() throws SQLException {
        List<Menu> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM MENU";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Menu(rs.getInt("ID_Drink"), rs.getInt("ID_Type"), rs.getString("Drinks"), rs.getInt("Price"), rs.getInt("Inventory")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }

    public List<Menu> getSearchMenu(String drinks) throws SQLException {
        List<Menu> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM MENU WHERE Drinks LIKE N'%" + drinks + "%'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Menu(rs.getInt("ID_Drink"), rs.getInt("ID_Type"), rs.getString("Drinks"), rs.getInt("Price"), rs.getInt("Inventory")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
