package DatabaseDAO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcDAO<T> implements DAO<T> {
    private QueryRunner queryRunner = null;
    private Class<T> type;

    public JdbcDAO() {
        queryRunner = new QueryRunner();
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length > 0) {
                if (types[0] instanceof Class) {
                    type = (Class<T>) types[0];
                }
            }
        }
    }

    @Override
    public void batch(Connection connection, String sql, Object... args) throws SQLException {
        queryRunner.batch(connection, sql, (Object[][]) args);
    }

    @Override
    public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException {
        return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
    }

    @Override
    public T get(Connection connection, String sql, Object... args) throws SQLException {
        return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
    }

    @Override
    public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException {
        return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
    }

    @Override
    public void update(Connection connection, String sql, Object... args) throws SQLException {
        queryRunner.update(connection, sql, args);
    }
}
