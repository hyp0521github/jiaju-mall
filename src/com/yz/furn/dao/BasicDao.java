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
        int rows = -1;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            rows = qr.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    // 查询多行数据
    public List<T> queryMulti(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        List<T> list = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
           list = qr.query(connection, sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // 查询单行数据
    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        T list = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            list = qr.query(connection, sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // 查询单行单列数据
    public Object queryScalar(String sql, Class<T> clazz, Object... params) {
        Connection connection = JDBCUtilsByDruid.getConnection();
        Object query = null;
        try {
            query = qr.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return query;
    }
}
