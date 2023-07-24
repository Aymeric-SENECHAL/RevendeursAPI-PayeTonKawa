package com.payetonkawa.payetonkawa.customers;

import com.payetonkawa.payetonkawa.orders.Orders;
import com.payetonkawa.payetonkawa.utils.DefaultEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customers extends DefaultEntity {

	@Column()
	private String name;

	@Column()
	private String username;

	@Column()
	private String firstname;

	@Column()
	private String lastname;

	@Column()
	private String postalCode;

	@Column()
	private String adressCity;

	@Column()
	private String profileFirstname;

	@Column()
	private String profileLastname;

	@Column()
	private String companyName;

	@OneToMany(mappedBy = "customers")
	//@JsonIgnore
	private List<Orders> ordersList;

}
