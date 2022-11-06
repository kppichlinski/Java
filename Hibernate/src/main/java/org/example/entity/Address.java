package org.example.entity;

import lombok.*;
import org.example.types.AddressType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Embeddable
public class Address {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;
    private String street;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;

}
