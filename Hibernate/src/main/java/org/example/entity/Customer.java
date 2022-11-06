package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@Cache(region = "customer", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "customer")
    @Cache(region = "customer", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Order> orders;

    @ElementCollection
    private List<Address> address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, optional = false)
    private CustomerDetails customerDetails;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    @OrderBy("id desc")
    private SortedSet<Review> reviews = new TreeSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(created, customer.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, created);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + lastname + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}


