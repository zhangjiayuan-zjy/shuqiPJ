package DatabaseDAO;

import Bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO extends JdbcDAO<User> {
    public List<User> getAll(User user) throws SQLException {
        String sql = "select * from traveluser";
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            return getForList(connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    public void addUser(User user) throws SQLException {
        String sql = "insert into traveluser (Email,UserName,Pass,State,DateJoined,DateLastModified) values (?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            Date date = new Date();
            update(connection, sql, user.getEmail(), user.getUserName(), user.getPass(), 1, date, date);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    /*Connection connection=null;
       try {
           connection=DataSource.getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       finally {
           connection.close();
       }
    */
    public User checkLogin(User user) {
        String sql = "select * from traveluser where UserName = ? and Pass = ?";
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            User user1 = get(connection, sql, user.getUserName(), user.getPass());
            return user1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getCount(User user) {
        String sql = "select count(UID) from traveluser where UserName = ?";
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            long count = getForValue(connection, sql, user.getUserName());
            if (count > 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUIDByName(String name) throws SQLException {
        Connection connection = null;
        User user = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from traveluser where UserName = ?";
            user = get(connection, sql, name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return user;
    }

    public List<User> getFriendByUID(int UID) throws SQLException {
        Connection connection = null;
        List<User> list = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from friendship where friend1 = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, UID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserByID(resultSet.getInt("friend2"));
                user.setDateJoined(getDateByID(user.getUID()));
                list.add(user);
            }
            String sql1 = "select * from friendship where friend2 = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, UID);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                User user = getUserByID(resultSet1.getInt("friend1"));
                user.setDateJoined(getDateByID(user.getUID()));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public User getUserByID(int UID) throws SQLException {
        Connection connection = null;
        User user = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from traveluser where UID = ?";
            user = get(connection, sql, UID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return user;
    }

    public List<User> getIdentifyByUID(int UID) throws SQLException {
        Connection connection = null;
        List<User> list = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from identifyFriend where getUser = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, UID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserByID(resultSet.getInt("pullUser"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public List<User> getUserByName(String name) throws SQLException {
        Connection connection = null;
        String name1 = "%" + name + "%";
        List<User> list = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from traveluser where UserName LIKE ?";
            list = getForList(connection, sql, name1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public void addIdentify(int uid1, int uid2) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "insert into identifyFriend (pullUser,getUser) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid1);
            preparedStatement.setInt(2, uid2);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void deleteIdentify(int UID, int UID1) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "delete from identifyFriend where pullUser = ? and getUser = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, UID);
            preparedStatement.setInt(2, UID1);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void deleteFriend(int uid1, int uid2) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "delete from friendship where friend1 = ? and friend2 = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid1);
            preparedStatement.setInt(2, uid2);
            preparedStatement.execute();
            String sql1 = "delete from friendship where friend1 = ? and friend2 = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, uid2);
            preparedStatement1.setInt(2, uid1);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void addFriendship(int uid1, int uid2) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "insert into friendship (friend1,friend2) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid1);
            preparedStatement.setInt(2, uid2);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void modifyState(int uid, int state) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "update traveluser set State = ? where UID = ?";
            update(connection, sql, state, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public String getDateByID(int UID) throws SQLException {
        Connection connection = null;
        Date date = null;
        String time = "";
        try {
            connection = DataSource.getConnection();
            String sql = "select * from traveluser where UID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, UID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                date = new Date(resultSet.getTimestamp("DateJoined").getTime());
                SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = formatime.format(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return time;
    }
}
