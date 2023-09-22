package com.yz.furn.dao.impl;

import com.yz.furn.dao.AdminDao;
import com.yz.furn.dao.BasicDao;
import com.yz.furn.entity.Admin;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AdminDaoImpl extends BasicDao<Admin> implements AdminDao {
    @Override
    public Admin queryByUsername(String username) {
        String sql = "SELECT * FROM admin WHERE username = ?";
        return querySingle(sql, Admin.class, username);
    }

    @Override
    public Admin queryAdminByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = MD5(?)";
        return querySingle(sql, Admin.class, username, password);
    }
}
