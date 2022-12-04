package com.github.carrental.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    private Long rentalId;

    @OneToOne
    @Column(name = "driver_license")
    @JoinColumn(columnDefinition = "driver_license")
    private User user;

    @OneToOne
    @Column(name = "car_reg_number")
    @JoinColumn(columnDefinition = "car_reg_number")
    private Car car;

    public Rent(User user, Car car) {
        this.user = user;
        this.car = car;
    }
}
