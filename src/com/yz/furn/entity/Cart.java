package com.yz.furn.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class Cart {
    public HashMap<Integer, CartItem> mp = new HashMap<>();

    public int getTotalCount() {
        int totalCount = 0;
        Set<Map.Entry<Integer, CartItem>> entries = mp.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            totalCount += ((CartItem) entry.getValue()).getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        Set<Integer> keys = mp.keySet();
        for (Integer key : keys) {
            CartItem cartItem = mp.get(key);
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }

    public void add(CartItem newItem, Integer id) {
        CartItem oldItem = mp.get(id);
        if (oldItem != null) {
            oldItem.setCount(oldItem.getCount() + newItem.getCount());
            //newItem.getPrice().multiply(new BigDecimal(oldItem.getCount()));
            oldItem.setTotalPrice(oldItem.getTotalPrice().add(newItem.getTotalPrice()));
        } else {
            mp.put(id, newItem);
        }
    }

    public void update(int id, int count) {
        CartItem cartItem = mp.get(id);
        cartItem.setCount(count);
        cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
    }

    public void delete(int id) {
        mp.remove(id);
    }

    public void clear() {
        mp.clear();
    }

    public HashMap<Integer, CartItem> getMp() {
        return mp;
    }

    public boolean isEmpty() {
        return mp.size() == 0;
    }
}
