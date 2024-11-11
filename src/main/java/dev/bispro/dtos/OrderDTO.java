package dev.bispro.dtos;

import java.util.Date;

public class OrderDTO {

    private Long orderId;
    private Float total;
    private Float net;
    private Date datetime;


    public OrderDTO(Long orderId, Float total, Float net, Date datetime) {
        this.orderId = orderId;
        this.total = total;
        this.net = net;
        this.datetime = datetime;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getNet() {
        return net;
    }

    public void setNet(Float net) {
        this.net = net;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
