package com.yz.furn.service.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.FurnDao;
import com.yz.furn.dao.impl.FurnDaoImpl;
import com.yz.furn.entity.Furn;
import com.yz.furn.service.FurnService;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class FurnServiceImpl extends BasicDao<Furn> implements FurnService {
    private FurnDao furnDao = new FurnDaoImpl();
    @Override
    public List<Furn> queryFurns() {
        return furnDao.queryFurns();
    }

    @Override
    public boolean addFurn(Furn furn) {
        int rows = furnDao.addFurn(furn);
        return rows == 1 ? true : false;
    }

    @Override
    public boolean delFurn(int id) {
        int rows = furnDao.delFurn(id);
        return rows == 1 ? true : false;
    }
}
