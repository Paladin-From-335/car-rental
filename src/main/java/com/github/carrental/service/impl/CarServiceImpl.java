package com.github.carrental.service.impl;

import com.github.carrental.exception.AlreadyExistException;
import com.github.carrental.exception.CarInUseException;
import com.github.carrental.exception.NotExistException;
import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.UserRentDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.repo.CarRepo;
import com.github.carrental.repo.RentRepo;
import com.github.carrental.service.CarService;
import com.github.carrental.util.Mapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final Mapper mapper;
    private final CarRepo carRepo;
    private final RentRepo rentRepo;

    @Override
    public CarDto saveCar(CarDto dto) {
        if (carRepo.findByCarRegNumber(dto.getCarRegNumber()).isPresent()) {
            throw new AlreadyExistException("Car with this registration number already exist");
        }
        Car car = mapper.dtoToEntity(dto);
        return mapper.entityToDto(carRepo.save(car));
    }

    @Override
    public void deleteCar(String carRegNumber) {
        if (rentRepo.findByCarRegNumber(carRegNumber).isPresent()) {
            throw new CarInUseException();
        }
        if (carRepo.findByCarRegNumber(carRegNumber).isEmpty()) {
            throw new NotExistException("Car with this registration number is not exist");
        }
        carRepo.deleteByCarRegNum(carRegNumber);
    }

    @Override
    public void changeCarAvailability(Boolean isUnavailable, String carRegNumber) {
        if (rentRepo.findByCarRegNumber(carRegNumber).isPresent()) {
            throw new CarInUseException();
        }
        if (carRepo.findByCarRegNumber(carRegNumber).isEmpty()) {
            throw new NotExistException("Car with this registration number is not exist");
        }
        carRepo.markUnavailable(isUnavailable, carRegNumber);
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
    public List<UserRentDto> getAllRented() {
        return mapper.mapListToDtoList(carRepo.getAllRented());
    }
}
