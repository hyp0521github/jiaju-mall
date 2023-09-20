package com.yz.furn.dao;

import com.yz.furn.entity.Member;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface MemberDao{

    // 保存用户信息
    boolean saveMember(Member member);

    // 查询用户信息
    Member queryMember(String username);
}
