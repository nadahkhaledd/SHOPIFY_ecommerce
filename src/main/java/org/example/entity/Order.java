package org.example.entity;
import org.example.enums.OrderStatus;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
public class Order{
    public Order() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetails;

    @Column(name = "user_id")
    @NotNull
    private int userId;
    @Column(name = "order_date")
    @NotNull
    private LocalDate date;
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    @Column(name = "total",nullable = false)
    private Integer total;

    public Order(int userId, LocalDate date, OrderStatus status, Integer total) {
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
