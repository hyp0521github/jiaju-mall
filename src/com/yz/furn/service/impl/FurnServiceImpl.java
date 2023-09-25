package com.yz.furn.service.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.FurnDao;
import com.yz.furn.dao.impl.FurnDaoImpl;
import com.yz.furn.entity.Furn;
import com.yz.furn.entity.Page;
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

    @Override
    public Furn get(int id) {
        return furnDao.queryFurnById(id);
    }

    @Override
    public boolean update(Furn furn) {
        int rows = furnDao.updateFurn(furn);
        return  rows == 1 ? true : false;
    }

    @Override
    public Page getPage(int pagesize, int pageno) {
        int totalSize = furnDao.queryTotal();
        Page<Furn> page = new Page<>();
        page.setTotalSize(totalSize);
        page.setPagesize(pagesize);
        page.setPageno(pageno);
        int totalPages = totalSize / pagesize;
        if(totalSize % pagesize > 0) {
            totalPages += 1;
        }
        page.setTotalPages(totalPages);
        int limit = pagesize;
        int offset = (pageno - 1) * pagesize;
        List<Furn> furns = furnDao.queryFurnByLimitAndOffset(limit, offset);
        page.setItems(furns);
        return page;
    }

    @Override
    public int getTotalByName(String name) {
        return furnDao.queryTotalByName(name);
    }

    @Override
    public Page<Furn> getPageByName(String name, int pagesize, int pageno) {
        int totalSize = furnDao.queryTotalByName(name);
        Page<Furn> page = new Page<>();
        page.setTotalSize(totalSize);
        page.setPagesize(pagesize);
        page.setPageno(pageno);
        int totalPages = totalSize / pagesize;
        if(totalSize % pagesize > 0) {
            totalPages += 1;
        }
        page.setTotalPages(totalPages);
        int limit = pagesize;
        int offset = (pageno - 1) * pagesize;
        List<Furn> furns = furnDao.queryFurnByName(name, limit, offset);
        page.setItems(furns);
        return page;
    }
}
