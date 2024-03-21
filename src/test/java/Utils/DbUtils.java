package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {
    public static List<Map<String, String>> fetch(String query) {
        String dbUrl = ConfigReader.read("dbUrl");
        String userName = ConfigReader.read("dbUserName");
        String password = ConfigReader.read("dbPassword");
        List<Map<String, String>> tableData = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             // Takes our queries executes them and brings the results back
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {

            // This Object gives us info about the data that is being returned by the query
            ResultSetMetaData rsm = rs.getMetaData();
            // as long as there are rows left next will keep on returning the true and will keep on moving the cursor to next line
            while (rs.next()) {
                // For every row we create a new map
                Map<String, String> rowMap = new LinkedHashMap<>();
                // To go through all the columns from a row
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    //getting the name of teh column
                    String key = rsm.getColumnName(i);
                    // getting the value of the row
                    String value = rs.getString(i);
                    rowMap.put(key, value);
                }
                tableData.add(rowMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableData;

    }
}
