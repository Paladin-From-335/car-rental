package com.github.carrental.service.impl;

import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.repo.CarRepo;
import com.github.carrental.service.CarService;
import com.github.carrental.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final Mapper mapper;
    private final CarRepo carRepo;

    @Override
    public void saveCar(CarDto dto) {
        Car car = mapper.dtoToEntity(dto);
        carRepo.save(car);
    }

    @Override
    public void deleteCar(String carRegNum) {
        carRepo.deleteByCarRegNum(carRegNum);
    }

    @Override
    public void markUnavailable(String carRegNum) {

    }

    @Override
    public List<CarDto> getAllCars() {
        return mapper.entityToDtoList(carRepo.findAll());
    }

    @Override
    public List<CarDto> getAvailable() {
        return mapper.entityToDtoList(carRepo.getAllAvailable());
    }

    @Override
    public Map<String, CarDto> getAllRented() {
        return mapper.entityToDtoMap(carRepo.getAllRented());
    }

}
