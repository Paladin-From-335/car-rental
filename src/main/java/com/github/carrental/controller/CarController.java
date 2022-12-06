package com.github.carrental.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.carrental.model.dto.AvailCarDto;
import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.RentDto;
import com.github.carrental.service.CarService;
import com.github.carrental.service.RentService;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final RentService rentService;

    @PostMapping("/save")
    private ResponseEntity<?> saveCar(@RequestBody CarDto dto) {
        return new ResponseEntity<>(carService.saveCar(dto), HttpStatus.OK);
    }

    @GetMapping("/all-cars")
    private List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/available-cars")
    private List<CarDto> getAllAvailableCars() {
        return carService.getAvailable();
    }

    @GetMapping("/rented-cars")
    private List<LinkedHashMap<String, Object>> getAllRentedCars() {
        return carService.getAllRented();
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteCar(@RequestBody @JsonProperty("car_reg_number") String carRegNumber) {
        carService.deleteCar(carRegNumber);
        return ResponseEntity.ok("SUCCESS");
    }

    @PutMapping("/change-availability")
    private ResponseEntity<?> changeCarAvailability(@RequestBody AvailCarDto dto) {
        carService.changeCarAvailability(
                dto.getIsUnavailable(),
                dto.getCarRegNumber()
        );
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/to-rent")
    private ResponseEntity<?> toRent(@RequestBody RentDto dto) {
        return new ResponseEntity<>(rentService.toRent(dto), HttpStatus.OK);
    }

    @PostMapping("/receive")
    private ResponseEntity<?> receiveCar(@RequestBody RentDto dto) {
        rentService.receiveCar(dto.getCarRegNumber());
        return ResponseEntity.ok("SUCCESS");
    }

}
