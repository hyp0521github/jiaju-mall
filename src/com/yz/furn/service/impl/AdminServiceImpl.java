package com.yz.furn.service.impl;

import com.yz.furn.dao.AdminDao;
import com.yz.furn.dao.impl.AdminDaoImpl;
import com.yz.furn.entity.Admin;
import com.yz.furn.service.AdminService;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public boolean isExistsAdmin(String username) {
        return adminDao.queryByUsername(username) == null ? false : true ;
    }

    @Override
    public Admin login(String username, String password) {
        return adminDao.queryAdminByUsernameAndPassword(username, password);
    }
}
