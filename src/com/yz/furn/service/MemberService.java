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

    // 查询用户
    boolean isExistUsernameMember(String username);
}
