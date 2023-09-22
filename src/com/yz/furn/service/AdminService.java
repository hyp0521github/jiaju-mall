package com.yz.furn.service;

import com.yz.furn.entity.Admin;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface AdminService {
    // 查询用户是否存在
    boolean isExistsAdmin(String username);

    // 登陆
    Admin login(String username, String password);
}
