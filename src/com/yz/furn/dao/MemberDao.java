package com.yz.furn.dao;

import com.yz.furn.entity.Member;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface MemberDao {

    // 保存用户信息
    int saveMember(Member member);

    // 根据username查询用户信息
    Member queryMemberByUsername(String username);

    // 根据username和password查询用户信息
    Member queryMemberByPasswordAndUsername(String username, String password);
}
