package com.github.carrental.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.RentDto;
import com.github.carrental.model.dto.UserDto;
import com.github.carrental.model.dto.UserRentDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.model.entity.Rent;
import com.github.carrental.model.entity.User;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Car dtoToEntity(CarDto carDto) {
        return modelMapper.map(carDto, Car.class);
    }

    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public CarDto entityToDto(Car car) {
        return modelMapper.map(car, CarDto.class);
    }

    public UserDto entityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public RentDto entityToDto(Rent rent) {
        return modelMapper.map(rent, RentDto.class);
    }

    public List<CarDto> entityToDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<UserRentDto> mapListToDtoList(List<Map<String, Object>> userRents) {
        return userRents.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public UserRentDto mapToDto(Map<String, Object> userRent) {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CarDto carDto = objMapper.convertValue(userRent, CarDto.class);
        UserRentDto userRentDto = objMapper.convertValue(userRent, UserRentDto.class);
        userRentDto.setCar(carDto);
        return userRentDto;
    }
}
