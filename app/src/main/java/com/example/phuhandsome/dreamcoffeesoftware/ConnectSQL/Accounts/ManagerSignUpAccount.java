package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.AccountModel;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.JDBCController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ManagerSignUpAccount {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public int countAccount = 0;
    public int resultcountAccount = 0;

    //public String permission;

    public ManagerSignUpAccount() {
        connection = jdbcController.ConnecttionData();
    }

    // Đếm xem trong bảng Account hiện tại đã có bao nhiêu Account
    private void CountAccount() throws SQLException {
        AccountModel accountModel = new AccountModel();
        List<Account> listAccount = accountModel.getAccountList();
        for (Account account : listAccount) {
            countAccount = account.getId_Account();
        }
        resultcountAccount = countAccount + 1;
    }

/*    // Kiểm tra xem người tạo là ai
    private void CheckPermisson() {
        LoginFragment loginFragment = new LoginFragment();
        permission = loginFragment.permissionLogin;
        if (permission.equals("manager")){
            permission = "manager";
        }else {
            permission = "customer";
        }
    }*/


    // đăng ký tài khoản
    public boolean Insert(Account account) throws SQLException {
        CountAccount();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "INSERT INTO ACCOUNT VALUES(" + resultcountAccount + ",'" + account.getUserName() + "','" + account.getPassWord() + "','" + account.getPermission() + "')";
        if (statement.executeUpdate(sql) > 0) {
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }
}
