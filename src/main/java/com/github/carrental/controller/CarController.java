package com.github.carrental.controller;

import static com.github.carrental.controller.handler.ResponseHandler.composeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.carrental.model.dto.AvailCarDto;
import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.RentDto;
import com.github.carrental.model.dto.UserRentDto;
import com.github.carrental.service.CarService;
import com.github.carrental.service.RentService;
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
        return composeResponse(carService.saveCar(dto), HttpStatus.OK);
    }

    @GetMapping("/all-cars")
    private ResponseEntity<?> getAllCars() {
        List<CarDto> cars = carService.getAllCars();
        if (cars.isEmpty()) {
            return composeResponse(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/available-cars")
    private ResponseEntity<?> getAllAvailableCars() {
        List<CarDto> cars = carService.getAvailable();
        if (cars.isEmpty()) {
            return composeResponse(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/rented-cars")
    private ResponseEntity<?> getAllRentedCars() {
        List<UserRentDto> rentInfo = carService.getAllRented();
        if (rentInfo.isEmpty()) {
            return composeResponse(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(rentInfo);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteCar(@RequestBody @JsonProperty("car_reg_number") String carRegNumber) {
        carService.deleteCar(carRegNumber);
        return composeResponse(HttpStatus.OK);
    }

    @PutMapping("/change-availability")
    private ResponseEntity<?> changeCarAvailability(@RequestBody AvailCarDto dto) {
        carService.changeCarAvailability(
                dto.getIsUnavailable(),
                dto.getCarRegNumber()
        );
        return composeResponse(HttpStatus.OK);
    }

    @PostMapping("/to-rent")
    private ResponseEntity<?> toRent(@RequestBody RentDto dto) {
        return composeResponse(rentService.toRent(dto), HttpStatus.OK);
    }

    @PostMapping("/receive")
    private ResponseEntity<?> receiveCar(@RequestBody RentDto dto) {
        rentService.receiveCar(dto.getCarRegNumber());
        return composeResponse(HttpStatus.OK);
    }

}
