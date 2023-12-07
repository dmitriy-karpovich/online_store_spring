package com.company.onlinestorespring.entity;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "order_information")
public class OrderInformation implements Serializable {
    @Serial
    private static final long serialVersionUID = -7622400659315471912L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_information_id")
    private Long id;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date", columnDefinition = "DATE")
    private LocalDate orderDate;

    @Column(name = "delivery_date", columnDefinition = "DATE")
    private LocalDate deliveryDate;

    @Column(name = "order_status")
    private String orderStatus;

    public OrderInformation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInformation that = (OrderInformation) o;
        return Objects.equals(id, that.id) && Objects.equals(deliveryAddress, that.deliveryAddress)
                && Objects.equals(orderDate, that.orderDate)
                && Objects.equals(deliveryDate, that.deliveryDate)
                && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryAddress, orderDate, deliveryDate, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderInformation{" +
                "id=" + id +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}