package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.MemberDao;
import com.yz.furn.entity.Member;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemberDaoImpl extends BasicDao<Member> implements MemberDao {
    @Override
    public int saveMember(Member member) {
        String sql = "INSERT INTO member VALUES(?, ?, MD5(?), ?)";
        return update(sql, member.getId(), member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public Member queryMemberByUsername(String username) {
        String sql = "SELECT id, username, `password`, email FROM member WHERE username = ?";
        return querySingle(sql, Member.class, username);
    }

    @Override
    public Member queryMemberByPasswordAndUsername(String username, String password) {
        String sql = "SELECT id, username, `password`, email FROM member WHERE username = ? and password = md5(?)";
        return querySingle(sql, Member.class, username,password);
    }
}
