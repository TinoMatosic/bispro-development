package dev.bispro.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_order")
public class Order
{

    @Id
    @Column(name = "o_orderid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "o_total")
    private float total;

    @Column(name = "o_net")
    private float net;
    @Column(name = "o_datetime")
    private Date datetime;


    public Long getO_orderId() {
        return orderId;
    }

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
