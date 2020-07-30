package DatabaseDAO;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSource {
    private static BasicDataSource basicDataSource;

    static {
        Properties properties = new Properties();
        InputStream inputStream = DataSource.class.getClassLoader().getResourceAsStream("Properties/dbcp.properties");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(inputStream);
            basicDataSource = BasicDataSourceFactory.createDataSource(properties);
            basicDataSource.setInitialSize(10);
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxTotal(50);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
