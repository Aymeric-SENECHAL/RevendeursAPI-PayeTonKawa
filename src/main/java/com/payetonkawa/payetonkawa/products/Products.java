package com.payetonkawa.payetonkawa.products;

import com.payetonkawa.payetonkawa.orders.Orders;
import com.payetonkawa.payetonkawa.utils.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Products extends DefaultEntity {

    @Column()
    private String price;

    @Column()
    private String description;

    @Column()
    private String color;

    @Column()
    private String stock;

    @ManyToMany(mappedBy = "productsList")
    //@JsonIgnore
    private List<Orders> ordersList;

}
