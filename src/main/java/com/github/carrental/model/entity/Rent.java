package com.github.carrental.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rented")
public class Rent {

    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "car_reg_number")
    private String carRegNumber;

    public Rent(String driverLicense, String carRegNumber) {
        this.driverLicense = driverLicense;
        this.carRegNumber = carRegNumber;
    }
}
