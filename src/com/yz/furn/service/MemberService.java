package com.yz.furn.service;

import com.yz.furn.entity.Member;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface MemberService {
    // 注册用户
    boolean registerMember(Member member);

    // 根据username查询用户是否存在
    boolean isExistMemberByUsername(String username);

    // 根据username,password查询用户是否存在
    Member login(String username,String password);
}
