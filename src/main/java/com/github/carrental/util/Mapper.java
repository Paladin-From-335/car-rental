package com.github.carrental.util;

import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.UserDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<CarDto> entityToDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public Map<String, CarDto> entityToDtoMap(Map<String, Car> map) {
        Map<String, CarDto> mappedMap = new HashMap<>();
        map.forEach((s, car) -> mappedMap.put(s, this.entityToDto(car)));
        return mappedMap;
    }
}
