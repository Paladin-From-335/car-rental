package com.github.carrental.repo;

import com.github.carrental.model.entity.Car;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM car_table WHERE car_reg_number=:curRegNum", nativeQuery = true)
    Optional<Car> findByCarRegNumber(String curRegNum);

    @Query(value = "DELETE FROM car_table WHERE car_reg_number=:carRegNum", nativeQuery = true)
    void deleteByCarRegNum(String carRegNum);
}
