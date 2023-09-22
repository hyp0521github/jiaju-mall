package com.yz.furn.service.impl;

import com.yz.furn.dao.MemberDao;
import com.yz.furn.dao.impl.MemberDaoImpl;
import com.yz.furn.entity.Member;
import com.yz.furn.service.MemberService;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberDaoImpl();

    @Override
    public boolean registerMember(Member member) {
        return memberDao.saveMember(member) == 1 ? true : false;
    }

    @Override
    public boolean isExistMemberByUsername(String username) {
        return memberDao.queryMemberByUsername(username) == null ? false : true;
    }

    @Override
    public Member login(String username, String password) {
        return memberDao.queryMemberByPasswordAndUsername(username, password);
    }
}
