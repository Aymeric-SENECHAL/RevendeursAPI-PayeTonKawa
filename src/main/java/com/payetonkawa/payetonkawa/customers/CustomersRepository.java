package com.payetonkawa.payetonkawa.customers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomersRepository extends JpaRepository<Customers, UUID> {

    Optional<Customers> findByEmail(String email);
}
