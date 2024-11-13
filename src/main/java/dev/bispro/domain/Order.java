package dev.bispro.domain;

import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order
{

    @EmbeddedId
    @Column(name = "orderid")
    private OrderId orderId;

    @Column(name = "total")
    private Float total;

    @Column(name = "net")
    private Float net;

    @Column(name = "datetime")
    private Date datetime;


    public Order(Float total, Float net, Date datetime) {
        setTotal(total);
        setNet(net);
        setDatetime(datetime);
    }

    public Order() {

    }

    public OrderId getOrderId() {
        return orderId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        if (total < 0) {
            throw DataValidationException.forInvalidInput("Total cannot be under 0");
        }
        this.total = total;
    }

    public float getNet() {
        return net;
    }

    public void setNet(Float net) {
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

    public record OrderId(@GeneratedValue @NonNull Long orderId) {}


    // only for testing
    public void setOrderId(OrderId l) {
        this.orderId = l;
    }
}