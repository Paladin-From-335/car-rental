package com.github.carrental.repo;

import com.github.carrental.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM car_table WHERE car_reg_number=:curRegNum", nativeQuery = true)
    Optional<Car> findByCarRegNumber(String curRegNum);

    @Query(value = "DELETE FROM car_table WHERE car_reg_number=:carRegNum", nativeQuery = true)
    void deleteByCarRegNum(String carRegNum);

    @Query(value = "SELECT * FROM car_table RIGHT JOIN rented using (car_reg_number) WHERE car_reg_number == null", nativeQuery = true)
    List<Car> getAllAvailable();

    @Query(value = "SELECT us.name, car.* FROM rented AS ren " +
            "INNER JOIN car_table car on car.car_reg_number = ren.car_reg_number " +
            "INNER JOIN user_table us on us.driver_license = ren.driver_license", nativeQuery = true)
    Map<String, Car> getAllRented();

}
