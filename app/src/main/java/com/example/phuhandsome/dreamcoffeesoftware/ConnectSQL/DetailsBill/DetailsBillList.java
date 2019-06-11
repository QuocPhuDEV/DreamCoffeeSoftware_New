package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailsBillList {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public DetailsBillList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<DetailsBill> getBill() throws SQLException {
        List<DetailsBill> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT Numtable, Drinks, Number FROM DETAILSBILL";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new DetailsBill(rs.getInt("Numtable"), rs.getString("Drinks"), rs.getInt("Number")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }

    public List<Request> getRequest() throws SQLException {
        List<Request> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT Numtable, Request FROM REQUEST";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Request(rs.getInt("Numtable"), rs.getString("Request")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
