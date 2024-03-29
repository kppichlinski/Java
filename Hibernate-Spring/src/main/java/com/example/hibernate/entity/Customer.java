package com.example.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Cacheable
@Cache(region = "customer", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String firstname;
    public String lastname;
    public LocalDateTime created;
    public LocalDateTime updated;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @Cache(region = "orders", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    Set<Order> orders;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Address> address;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, optional = false)
    private CustomerDetails customerDetails;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    //@OrderBy("id desc")
    //@SortNatural
    @SortComparator(SortById.class)
    private SortedSet<Review> reviews = new TreeSet<>();

    public static class SortById implements Comparator<Review> {
        @Override
        public int compare(Review o1, Review o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstname, customer.firstname) &&
                Objects.equals(lastname, customer.lastname) &&
                Objects.equals(created, customer.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, created);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public SortedSet<Review> getReviews() {
        return reviews;
    }

    public void setReviews(SortedSet<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
