package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Voucher;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.Sale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoucherList {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public VoucherList() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Voucher> getVoucherList() throws SQLException {
        List<Voucher> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "SELECT * FROM VOUCHER";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Voucher(rs.getInt("ID_Voucher"), rs.getString("Voucher"), rs.getInt("NumPercent")));// Đọc dữ liệu từ ResultSet
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
