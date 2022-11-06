package com.example.hibernate.repository;

import com.example.hibernate.entity.Customer;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c " +
            "inner join fetch c.orders " +
            "where id=:id")
    @QueryHints(
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_READONLY, value = "true")
    )
    Customer findByIdWithOrders(@Param("id") Long id);

    @Modifying
    @Query("delete c from Customer c")
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    void deleteAllRecords();
}
