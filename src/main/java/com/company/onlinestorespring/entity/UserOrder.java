package com.company.onlinestorespring.entity;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_order")
public class UserOrder implements Serializable {
    @Serial
    private static final long serialVersionUID = -6224978553571534616L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_order_id")
    private Long id;

    @Column(name = "purchase_quantity")
    private Integer purchasedQuantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_information_id")
    private OrderInformation orderInformation;

    public UserOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(Integer purchaseQuantity) {
        this.purchasedQuantity = purchaseQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderInformation getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(OrderInformation orderInformation) {
        this.orderInformation = orderInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return Objects.equals(id, userOrder.id) &&
               Objects.equals(purchasedQuantity, userOrder.purchasedQuantity) &&
               Objects.equals(user, userOrder.user) &&
               Objects.equals(product, userOrder.product) &&
               Objects.equals(orderInformation, userOrder.orderInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchasedQuantity, user, product, orderInformation);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", purchaseQuantity=" + purchasedQuantity +
                ", user=" + user +
                ", product=" + product +
                ", orderInformation=" + orderInformation +
                '}';
    }
}
