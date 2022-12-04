package com.github.carrental.repo;

import com.github.carrental.model.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepo extends JpaRepository<Rent, Long> {

    @Query(value = "DELETE FROM rented WHERE driver_license =:license", nativeQuery = true)
    void deleteByDriverLicense(String license);
}
