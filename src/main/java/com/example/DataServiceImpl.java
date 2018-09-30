package com.example;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class DataServiceImpl implements DataService {
    @Override
    public ArrayList<String> getDataFromDB(Connection connection) throws SQLException {
        return null;
    }
}
