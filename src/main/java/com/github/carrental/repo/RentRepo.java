package com.github.carrental.repo;

import com.github.carrental.model.entity.Rent;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RentRepo extends JpaRepository<Rent, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM rented WHERE car_reg_number=:carRegNumber", nativeQuery = true)
    void deleteByCarNumber(String carRegNumber);

    @Query(value = "SELECT * FROM rented WHERE car_reg_number=:carRegNumber", nativeQuery = true)
    Optional<Rent> findByCarRegNumber(String carRegNumber);

}
