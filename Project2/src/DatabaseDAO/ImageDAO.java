package DatabaseDAO;

import Bean.Image;
import Bean.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ImageDAO extends JdbcDAO<Image> {

    public Image getImageByID(int imageID) throws SQLException {
        Image image = null;
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimage where ImageID = ?";
            image = get(connection, sql, imageID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return getWholeImage(image);
        //return image;
    }

    public List<Image> getFavorMost() throws SQLException {
        Connection connection = null;
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimage order by FavorNumber DESC limit 3";
            //String sql="select ImageID,count(ImageID) from travelimagefavor group by ImageID order by count(ImageID) limit 3";
            list = getForList(connection, sql);
            //String sql1="select * from travelimage where ImageID = ?";
            for (Image image : list) {
                //Date date=getDate(image.getImageID());
                //image.setDate(date);
                String date = getDate(image.getImageID());
                image.setDate(date);
                list1.add(image);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list1;
    }

    public List<Image> getDateLast() throws SQLException {
        Connection connection = null;
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimage order by Date DESC limit 6";
            list = getForList(connection, sql);
            /*for (Image image:list){
                Date date=getDate(image.getImageID());
                image.setDate(date);
                //Image image1=getWholeImage(image);
                list1.add(image);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public String getDate(int imageID) throws SQLException {
        Connection connection = null;
        Date date = null;
        String time = "";
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimage where ImageID =" + imageID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                date = new Date(resultSet.getTimestamp("Date").getTime());
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

    public User getUserByUID(int UID) throws SQLException {
        Connection connection = null;
        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from traveluser where UID = ?";
            user = userDAO.get(connection, sql, UID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return user;
    }

    public List<User> getListUser(List<Image> list) throws SQLException {
        List<User> list1 = new ArrayList<>();
        Connection connection = null;
        UserDAO userDAO = new UserDAO();
        try {
            connection = DataSource.getConnection();
            String sql = "";
            for (Image image : list) {
                User user = getUserByUID(image.getUID());
                list1.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list1;
    }

    /*public long getFavorNumber(int imageID) throws SQLException {
        Connection connection = null;
        long number = 0;
        try {
            connection = DataSource.getConnection();
            String sql = "select count(ImageID) from travelimagefavor where ImageID = ?";
            number = getForValue(connection, sql, imageID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return number;
    }*/

    public String getCityName(int cityID) throws SQLException {
        Connection connection = null;
        String cityName = "";
        try {
            connection = DataSource.getConnection();
            String sql = "select AsciiName from geocities where GeoNameID = ?";
            cityName = getForValue(connection, sql, cityID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return cityName;
    }

    public String getCountry(String countryID) throws SQLException {
        Connection connection = null;
        String countryName = "";
        try {
            connection = DataSource.getConnection();
            String sql = "select Country_RegionName from geocountries_regions where ISO = ?";
            countryName = getForValue(connection, sql, countryID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return countryName;
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
    private Image getWholeImage(Image image) throws SQLException {
        String cityName = getCityName(image.getCityCode());
        String countryName = getCountry(image.getCountry_RegionCodeISO());
        //long number = getFavorNumber(image.getImageID());
        //image.setFavorNumber(number);
        String date=getDate(image.getImageID());
        image.setDate(date);
        image.setAsciiName(cityName);
        image.setCountryName(countryName);
        return image;
    }

    //testIsFavor;
    public boolean isFavor(int imageID, int UID) throws SQLException {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimagefavor where UID = ? and ImageID = ?";
            Image image = get(connection, sql, UID, imageID);
            if (image != null)
                flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return flag;
    }

    public void addFavor(Image image, User user) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "insert into travelimagefavor (UID,ImageID) values(?,?)";
            update(connection, sql, user.getUID(), image.getImageID());
            String sql1 = "update travelimage set FavorNumber = ? where ImageID = ?";
            update(connection, sql1, image.getFavorNumber() + 1, image.getImageID());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void deleteFavor(int imageID, int UID) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "delete from travelimagefavor where UID = ? and ImageID = ?";
            update(connection, sql, UID, imageID);
            String sql1 = "update travelimage set FavorNumber = ? where ImageID = ?";
            Image image = getImageByID(imageID);
            update(connection, sql1, (image.getFavorNumber() - 1), imageID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<Image> getAllImage(String order, int page) throws SQLException {
        Connection connection = null;
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = (order.equals("date")) ? "select * from travelimage order by Date DESC limit ? , 5" : "select * from travelimage order by FavorNumber DESC limit ? , 5";
            list = getForList(connection, sql, page);
            /*for (Image image:list){
                Image image1=getWholeImage(image);
                list1.add(image1);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public List<Image> getImageByTitle(String title, String order, int page) throws SQLException {
        Connection connection = null;
        String title1 = "%" + title + "%";
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = (order.equals("date")) ? "select * from travelimage where Title LIKE ? order by Date DESC limit ? , 5" : "select * from travelimage where Title LIKE ? order by FavorNumber DESC limit ? , 5";
            list = getForList(connection, sql, title1, page);
            /*for (Image image:list){
                Image image1=getWholeImage(image);
                list1.add(image1);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public List<Image> getImageByContent(String content, String order, int page) throws SQLException {
        String content1 = "%" + content + "%";
        Connection connection = null;
        List<Image> list = null;
        try {
            connection = DataSource.getConnection();
            String sql = (order.equals("date")) ? "select * from travelimage where Content LIKE ? order by Date DESC limit ? , 5" : "select * from travelimage where Content LIKE ? order by FavorNumber DESC limit ? , 5";
            list = getForList(connection, sql, content1, page);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public List<Image> getImageByUID(int UID) throws SQLException {
        Connection connection = null;
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimage where UID = ?";
            list = getForList(connection, sql, UID);
            /*for (Image image:list){
                Image image1=getWholeImage(image);
                list1.add(image1);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    public List<Image> getFavorByID(int UID) throws SQLException {
        Connection connection = null;
        List<Image> list = null;
        List<Image> list1 = new ArrayList<>();
        try {
            connection = DataSource.getConnection();
            String sql = "select * from travelimagefavor where UID = ?";
            list = getForList(connection, sql, UID);
            for (Image image : list) {
                Image image1 = getImageByID(image.getImageID());
                list1.add(image1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list1;
    }

    public void deleteImage(int imageID) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "delete from travelimage where ImageID = ?";
            update(connection, sql, imageID);
            String sql1 = "delete from travelimagefavor where ImageID = ?";
            update(connection, sql1, imageID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void addImage(Image image) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            String sql = "insert into travelimage (Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content,Date,Author) values(?,?,?,?,?,?,?,?,?)";
            update(connection, sql, image.getTitle(), image.getDescription(), image.getCityCode(), image.getCountry_RegionCodeISO(), image.getUID(), image.getPath(), image.getContent(), image.getDate(), image.getAuthor());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void modifyImage(String title, String content, String des, int city, String country, int imageID, String author) throws SQLException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            Date date = new Date();
            SimpleDateFormat formatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatime.format(date);
            String sql = "update travelimage set Title = ? ,Description = ? ,CityCode = ? ,Content = ? , Country_RegionCodeISO = ? ,Date = ? ,Author = ? where ImageID = ?";
            update(connection, sql, title, des, city, content, country, time, author, imageID);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public long getCountInit() throws SQLException {
        Connection connection = null;
        long count = 0;
        try {
            connection = DataSource.getConnection();
            String sql = "select count(ImageID) from travelimage where ImageID > 0";
            count = getForValue(connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return count;
    }

    public long getCountTitle(String title) throws SQLException {
        Connection connection = null;
        String title1 = "%" + title + "%";
        long count = 0;
        try {
            connection = DataSource.getConnection();
            String sql = "select count(*) from travelimage where Title LIKE ? ";
            count = getForValue(connection, sql, title1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return count;
    }

    public long getCountContent(String content) throws SQLException {
        String content1 = "%" + content + "%";
        Connection connection = null;
        long count = 0;
        try {
            connection = DataSource.getConnection();
            String sql = "select count(*) from travelimage where Content LIKE ?";
            count = getForValue(connection, sql, content1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return count;
    }
}
