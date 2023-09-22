package com.yz.furn.test;

import com.yz.furn.entity.Furn;
import com.yz.furn.service.impl.FurnServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class TestFurn {
    public static void main(String[] args) {
        FurnServiceImpl furnService = new FurnServiceImpl();
        List<com.yz.furn.entity.Furn> furns = furnService.queryFurns();
        for (com.yz.furn.entity.Furn furn : furns) {
            System.out.println(furn);
        }
    }

    @Test
    public void testFurn() {
        FurnServiceImpl furnService = new FurnServiceImpl();
        Furn furn = new Furn(null,"欧美风格小桌子", "熊猫家居", new BigDecimal(180.00), 666, 7, "assets/images/product-image/6.jpg");
        furnService.addFurn(furn);
    }
}
