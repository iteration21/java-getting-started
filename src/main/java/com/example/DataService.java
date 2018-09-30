package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataService {
    ArrayList<String> getDataFromDB(Connection connection) throws SQLException;
}
