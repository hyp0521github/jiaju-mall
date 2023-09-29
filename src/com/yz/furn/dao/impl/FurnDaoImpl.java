package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.FurnDao;
import com.yz.furn.entity.Furn;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
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
        return update(sql, furn.getname(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    }

    @Override
    public int delFurn(int id) {
        String sql = "DELETE FROM furn WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` FROM furn WHERE id = ?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "UPDATE furn SET name = ?, maker = ?, price = ?, sales = ?, stock = ? WHERE id = ?";
        return update(sql, furn.getname(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getId());
    }

    @Override
    public int updateFurnInSalesAndStock(int count, int id) {
        String sql = "UPDATE furn SET sales = sales + ?, stock = stock - ? WHERE id = ?";
        return update(sql, count, count, id);
    }

    @Override
    public int queryTotal() {
        String sql = "SELECT count(*) FROM furn";
        return ((Number) queryScalar(sql, Furn.class, null)).intValue();
    }

    @Override
    public List<Furn> queryFurnByLimitAndOffset(int limit, int offset) {
        String sql = "SELECT * FROM furn LIMIT ? OFFSET ?";
        return queryMulti(sql, Furn.class, limit, offset);
    }

    @Override
    public List<Furn> queryFurnByName(String name, int limit, int offset) {
        String sql = "SELECT `id`,`name`,`maker`,`price`,`sales`,`stock`,`img_path` FROM furn WHERE name LIKE ? LIMIT ? OFFSET ?";
        return queryMulti(sql, Furn.class, '%' + name + '%', limit, offset);
    }

    @Override
    public int queryTotalByName(String name) {
        String sql = "SELECT count(*) FROM furn WHERE name LIKE ?";
        return ((Number) queryScalar(sql, Furn.class, '%' + name + '%')).intValue();
    }
}
