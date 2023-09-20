package com.yz.furn.service.impl;

import com.yz.furn.dao.MemberDao;
import com.yz.furn.dao.impl.MemberImpl;
import com.yz.furn.entity.Member;
import com.yz.furn.service.MemberService;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberImpl();

    // 注册用户
    @Override
    public boolean registerMember(Member member) {
        return memberDao.saveMember(member);
    }

    // 查询用户
    @Override
    public boolean isExistUsernameMember(String username) {
        Member member = memberDao.queryMember(username);
        return member == null ? false : true;
    }
}
