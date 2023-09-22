package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.FurnDao;
import com.yz.furn.entity.Furn;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class FurnDaoImpl extends BasicDao<Furn> implements FurnDao {
    @Override
    public List<Furn> queryFurns() {
        String sql = "SELECT id, name, maker, price, sales, stock, img_path imgPath FROM furn";
        return queryMulti(sql, Furn.class, null);
    }

    @Override
    public int addFurn(Furn furn) {
        String sql = "INSERT INTO furn(`id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path`) VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        return update(sql, furn.getname(), furn.getMaker(), furn.getPrice(),furn.getSales(), furn.getStock(),furn.getImgPath());
    }

    @Override
    public int delFurn(int id) {
        String sql = "DELETE FROM furn WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public Furn queryIdFurn(int id) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` FROM furn WHERE id = ?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int updateIdFurn(Furn furn, int id) {
        String sql = "UPDATE furn SET name = ?, maker = ?, price = ?, sales = ?, stock = ? WHERE id = ?";
        return update(sql, furn.getname(), furn.getMaker(),furn.getPrice(),furn.getSales(),furn.getStock(),furn.getId());
    }
}
