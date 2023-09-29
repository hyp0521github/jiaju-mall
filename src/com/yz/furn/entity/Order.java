package com.yz.furn.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class Order {
    private String id; // 订单id
    private Integer memberId; // 客户id
    private Date orderDate; // 订单日期
    private BigDecimal totalAmount; // 订单总价
    private Integer status; // 订单状态

    public Order() {
    }

    public Order(String id, Integer memberId, Date orderDate, BigDecimal totalAmount, Integer status) {
        this.id = id;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", memberId=" + memberId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
