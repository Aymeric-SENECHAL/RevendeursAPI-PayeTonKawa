package com.payetonkawa.payetonkawa.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payetonkawa.payetonkawa.customers.Customers;
import com.payetonkawa.payetonkawa.products.Products;
import com.payetonkawa.payetonkawa.utils.DefaultEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Orders extends DefaultEntity {

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "customers_id")
    private Customers customers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    @JsonIgnore
    List<Products> productsList;

}
