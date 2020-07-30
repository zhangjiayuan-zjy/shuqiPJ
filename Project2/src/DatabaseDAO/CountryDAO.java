package DatabaseDAO;

import Bean.Country;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CountryDAO extends JdbcDAO<Country> {
    public List<Country> getAllCountry(int size) throws SQLException {
        Connection connection = null;
        List<Country> list = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from geocountries_regions order by Population DESC limit ?";
            list = getForList(connection, sql, size);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
}
