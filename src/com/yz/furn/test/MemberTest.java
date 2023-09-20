package com.yz.furn.test;


import com.yz.furn.dao.MemberDao;
import com.yz.furn.dao.impl.MemberImpl;
import com.yz.furn.entity.Member;
import com.yz.furn.utils.JDBCUtilsByDruid;

import java.sql.Connection;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class MemberTest {
    private MemberDao memberDao = new MemberImpl();

    @org.junit.Test
    public void test() {
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }

    @org.junit.Test
    public void test1() {
        Member member = new Member(null, "yz", "123", "123@qq.com");
        boolean b = memberDao.saveMember(member);
        System.out.println(b);
    }

    @org.junit.Test
    public void test2() {
        Member admin = memberDao.queryMember("admin");
        System.out.println(admin);
    }
}
