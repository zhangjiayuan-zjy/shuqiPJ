package DatabaseDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    /**
     *
     */
    void batch(Connection connection, String sql, Object... args) throws SQLException;

    /**返回一个具体的值
     *
     */
    <E> E getForValue(Connection connection,String sql,Object  ... args)throws SQLException;

    /**返回一个T的对象
     *
     */
    T get(Connection connection,String sql,Object ... args) throws SQLException;

    /**返回一个T的集合
     *
     */
    List<T> getForList(Connection connection,String sql,Object ... args) throws SQLException;
    /**
     *增删改
     */
    void update(Connection connection,String sql,Object... args) throws SQLException;
}
