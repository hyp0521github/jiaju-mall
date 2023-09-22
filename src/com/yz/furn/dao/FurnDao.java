package com.yz.furn.dao;

import com.yz.furn.entity.Furn;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface FurnDao {
    // 查询家居列表
    List<Furn> queryFurns();

    // 添加家居
    int addFurn(Furn furn);

    // 删除家居
    int delFurn(int id);

    // 查询单个家居
    Furn queryIdFurn(int id);

    // 修改家居信息
    int updateIdFurn(Furn furn, int id);
}
