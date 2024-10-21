package dev.bispro.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @Column(name = "orderid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "total")
    private float total;

    @Column(name = "net")
    private float net;

    @Column(name = "datetime")
    private Date datetime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getNet() {
        return net;
    }

    public void setNet(float net) {
        this.net = net;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
