package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.MemberDao;
import com.yz.furn.entity.Member;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemberImpl extends BasicDao<Member> implements MemberDao {
    // 保存用户信息
    @Override
    public boolean saveMember(Member member) {
        String sql = "INSERT INTO member VALUES(?, ?, MD5(?), ?)";
        int update = update(sql, member.getId(), member.getUsername(), member.getPassword(), member.getEmail());
        return update == 1 ? true : false;
    }

    // 查询用户信息
    @Override
    public Member queryMember(String username) {
        String sql = "SELECT id, username, `password`, email FROM member WHERE username = ?";
        return querySingle(sql, Member.class, username);
    }
}
