package com.yz.furn.dao;

import com.yz.furn.entity.Furn;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface FurnDao {
    // 查询所有家居列表
    List<Furn> queryFurns();

    // 添加家居
    int addFurn(Furn furn);

    // 删除家居
    int delFurn(int id);

    // 查询单个家居
    Furn queryFurnById(int id);

    // 修改家居信息
    int updateFurn(Furn furn);

    // 查询数据总量
    int queryTotal();

    // 获取分页数据
    List<Furn> queryFurnByLimitAndOffset(int limit, int offset);

    // 根据家居名称查询家居信息
    List<Furn> queryFurnByName(String name, int limit, int offset);

    // 根据家居名称获取对应的家居数量
    int queryTotalByName(String name);
}
