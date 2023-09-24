package com.yz.furn.service;

import com.yz.furn.entity.Furn;
import com.yz.furn.entity.Page;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface FurnService {
    List<Furn> queryFurns();

    boolean addFurn(Furn furn);

    boolean delFurn(int id);

    Furn get(int id);

    boolean update(Furn furn);

    Page getPage(int pagesize, int pageno);
}
