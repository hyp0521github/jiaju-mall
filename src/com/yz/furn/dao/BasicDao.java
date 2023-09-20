package com.yz.furn.dao;

import com.yz.furn.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class BasicDao<T> {
    private QueryRunner qr = new QueryRunner();

    // dml操作
    public int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int rows = qr.update(connection, sql, params);
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    // 查询多行数据
    public List<T> queryMulti(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> list = qr.query(connection, sql, new BeanListHandler<T>(clazz), params);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    // 查询单行数据
    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T list = qr.query(connection, sql, new BeanHandler<T>(clazz), params);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }

    // 查询单行单列数据
    public Object queryScalar(String sql, Class<T> clazz, Object... params) {
        Connection connection = JDBCUtilsByDruid.getConnection();
        try {
            Object query = qr.query(connection, sql, new ScalarHandler(), params);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeConnection(null, null, connection);
        }
    }
}
