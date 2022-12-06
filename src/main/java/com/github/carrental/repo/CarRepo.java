package com.github.carrental.repo;

import com.github.carrental.model.entity.Car;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM car_table WHERE car_reg_number=:curRegNumber", nativeQuery = true)
    Optional<Car> findByCarRegNumber(String curRegNumber);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM car_table WHERE car_reg_number=:carRegNumber", nativeQuery = true)
    void deleteByCarRegNum(String carRegNumber);

    @Query(value = "SELECT car.* FROM car_table car  " +
            "LEFT JOIN rented ren on car.car_reg_number = ren.car_reg_number " +
            "WHERE ren.car_reg_number IS NULL", nativeQuery = true)
    List<Car> getAllAvailable();

    @Query(value = "SELECT us.username, car.* FROM rented ren " +
            "INNER JOIN car_table car on car.car_reg_number = ren.car_reg_number " +
            "INNER JOIN users us on us.driver_license = ren.driver_license", nativeQuery = true)
    List<Map<String, Object>> getAllRented();

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE car_table SET tmp_unavailable=:isUnavailable" +
            " WHERE car_reg_number=:carRegNumber", nativeQuery = true)
    void markUnavailable(Boolean isUnavailable, String carRegNumber);
}
