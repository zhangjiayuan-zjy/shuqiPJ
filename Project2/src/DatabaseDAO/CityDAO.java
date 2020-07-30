package DatabaseDAO;

import Bean.City;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CityDAO extends JdbcDAO<City> {
    public List<City> getCityByCountry(String ISO) throws SQLException {
        Connection connection = null;
        List<City> list = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from geocities where Country_RegionCodeISO = ? limit 20";
            list = getForList(connection, sql, ISO);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
}
