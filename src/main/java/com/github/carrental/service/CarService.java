package com.github.carrental.service;

import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.UserRentDto;
import java.util.List;

public interface CarService {

    CarDto saveCar(CarDto dto);

    void deleteCar(String carRegNumber);

    void changeCarAvailability(Boolean isUnavailable, String carRegNum);

    List<CarDto> getAllCars();

    List<CarDto> getAvailable();

    List<UserRentDto> getAllRented();

}
