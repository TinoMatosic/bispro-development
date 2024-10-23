package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
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


    public Order(float total, float net, Date datetime) {
        setTotal(total);
        setNet(net);
        setDatetime(datetime);
    }

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        if (total < 0) {
            throw DataValidationException.forInvalidInput("Total cannot be under 0");
        }
        this.total = total;
    }

    public float getNet() {
        return net;
    }

    public void setNet(float net) {
        if (net < 0) {
            throw DataValidationException.forInvalidInput("Net cannot be under 0");
        }
        this.net = net;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        Date now = new Date();
        if (datetime == null || datetime.before(now)) {
            throw new IllegalArgumentException("Datetime cannot be null or in the past");
        }
        this.datetime = datetime;
    }
}