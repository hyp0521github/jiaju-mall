package com.yz.furn.dao;

import com.yz.furn.entity.Admin;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface AdminDao {
    // 根据账号查询管理员账号是否存在
    Admin queryByUsername(String username);

    // 根据账号密码查询管理员账号密码是否正确
    Admin queryAdminByUsernameAndPassword(String username, String password);
}
