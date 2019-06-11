package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuItemSelectedWithDrinks {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public static int orderNumber =0;

    public MenuItemSelectedWithDrinks() {
        connection = jdbcController.ConnecttionData();
    }

    public List<Menu> getMenuList() throws SQLException {
        List<Menu> list = new ArrayList<>();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.

        // đổ dữ liệu từ danh sách món được chọn vào list để tạo điều kiện truy vấn
        MenuFragment menuFragment = new MenuFragment();
        ArrayList ID_DrinksList = new ArrayList();
        ID_DrinksList = menuFragment.listMenuSelected;

        for (int i = 0; i < ID_DrinksList.size(); i++) {
            String sql = "SELECT * FROM MENU WHERE ID_Drink = '" + ID_DrinksList.get(i) + "'";
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(new Menu(rs.getInt("ID_Drink"), rs.getInt("ID_Type"), rs.getString("Drinks"), rs.getInt("Price"), rs.getInt("Inventory")));// Đọc dữ liệu từ ResultSet
            }
        }
        connection.close();// Đóng kết nối
        return list;
    }
}
