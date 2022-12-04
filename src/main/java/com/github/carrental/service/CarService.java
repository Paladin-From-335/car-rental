package com.github.carrental.service;

import com.github.carrental.model.dto.CarDto;
import java.util.List;
import java.util.Map;

public interface CarService {

    void saveCar(CarDto dto);

    void deleteCar(String carRegNum);

    void markUnavailable(String carRegNum);

    List<CarDto> getAllCars();

    List<CarDto> getAvailable();

    Map<String, CarDto> getAllRented();

}
