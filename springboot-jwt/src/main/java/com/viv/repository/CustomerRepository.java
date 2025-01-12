package com.viv.repository;

import com.viv.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Optional<Customer> findByEmail(String email);
}
