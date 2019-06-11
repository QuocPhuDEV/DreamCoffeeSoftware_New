package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL;

import java.sql.Connection;

public class JDBCController {
    JDBCModel JDBCModel = new JDBCModel();

    public Connection ConnecttionData() {
        return JDBCModel.getConnectionOf();
    }
}
