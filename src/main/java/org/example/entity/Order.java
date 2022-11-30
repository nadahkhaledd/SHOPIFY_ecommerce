package org.example.entity;
import org.example.enums.OrderStatus;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @NotNull
    private Long userId;
    @Column(name = "order_date")
    @NotNull
    private LocalDate date;
    @Column(name = "status")
    @NotNull
    private OrderStatus status;
    @Column(name = "total")
    @NotNull
    private int total;

    public Order() {
    }

    public Order(Long userId, LocalDate date, OrderStatus status, int total) {
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
