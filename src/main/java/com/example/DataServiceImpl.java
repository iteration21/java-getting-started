package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class DataServiceImpl implements DataService {
    @Override
    public ArrayList<String> getDataFromDB(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
            output.add("Read from DB: " + rs.getTimestamp("tick"));
        }

        enrichWithSystemData(output);

        return output;
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }

    public void enrichWithSystemData(ArrayList<String> output) {

        output.add("i21> get system data ...");
        output.add("Available processors (cores): "+Runtime.getRuntime().availableProcessors());
        output.add("Free memory (bytes): "+Runtime.getRuntime().freeMemory());
        long maxMemory = Runtime.getRuntime().maxMemory();
        output.add("Maximum memory (bytes): "+(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
        output.add("Total memory available to JVM (bytes): "+Runtime.getRuntime().totalMemory());

        File[] roots = File.listRoots();

        for (File root : roots) {
            output.add("File system root: " + root.getAbsolutePath());
            output.add("Total space (bytes): " + root.getTotalSpace());
            output.add("Free space (bytes): " + root.getFreeSpace());
            output.add("Usable space (bytes): " + root.getUsableSpace());
        }

        output.add("i21> systemProperty: java.version" + ": " + System.getProperty("java.version"));
        output.add("i21> systemProperty: java.vendor" + ": " + System.getProperty("java.vendor"));
        output.add("i21> systemProperty: java.vendor.url" + ": " + System.getProperty("java.vendor.url"));
        output.add("i21> systemProperty: java.home" + ": " + System.getProperty("java.home"));
        output.add("i21> systemProperty: java.vm.specification.version" + ": " + System.getProperty("java.vm.specification.version"));
        output.add("i21> systemProperty: java.vm.specification.vendor" + ": " + System.getProperty("java.vm.specification.vendor"));
        output.add("i21> systemProperty: java.vm.specification.name" + ": " + System.getProperty("java.vm.specification.name"));
        output.add("i21> systemProperty: java.vm.version" + ": " + System.getProperty("java.vm.version"));
        output.add("i21> systemProperty: java.vm.vendor" + ": " + System.getProperty("java.vm.vendor"));
        output.add("i21> systemProperty: java.vm.name" + ": " + System.getProperty("java.vm.name"));
        output.add("i21> systemProperty: java.specification.version" + ": " + System.getProperty("java.specification.version"));
        output.add("i21> systemProperty: java.specification.vendor" + ": " + System.getProperty("java.specification.vendor"));
        output.add("i21> systemProperty: java.specification.name" + ": " + System.getProperty("java.specification.name"));
        output.add("i21> systemProperty: java.class.version" + ": " + System.getProperty("java.class.version"));
        output.add("i21> systemProperty: ava.class.path" + ": " + System.getProperty("ava.class."));
        output.add("i21> systemProperty: java.library.path" + ": " + System.getProperty("java.library.path"));
        output.add("i21> systemProperty: java.io.tmpdir" + ": " + System.getProperty("java.io.tmpdir"));
        output.add("i21> systemProperty: java.compiler" + ": " + System.getProperty("java.compiler"));
        output.add("i21> systemProperty: java.ext.dirs" + ": " + System.getProperty("java.ext.dirs"));
        output.add("i21> systemProperty: os.name" + ": " + System.getProperty("os.name"));
        output.add("i21> systemProperty: os.arch" + ": " + System.getProperty("os.arch"));
        output.add("i21> systemProperty: os.version" + ": " + System.getProperty("os.version"));
        output.add("i21> systemProperty: file.separator" + ": " + System.getProperty("file.separator"));
        output.add("i21> systemProperty: path.separator " + ": " + System.getProperty("path.separator"));
        output.add("i21> systemProperty: line.separator" + ": " + System.getProperty("line.separator"));
        output.add("i21> systemProperty: user.name" + ": " + System.getProperty("user.name"));
        output.add("i21> systemProperty: user.home" + ": " + System.getProperty("user.home"));
        output.add("i21> systemProperty: user.dir" + ": " + System.getProperty("user.dir"));


    }
}
