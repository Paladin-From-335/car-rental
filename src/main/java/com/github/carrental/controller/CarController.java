package com.github.carrental.controller;

import com.github.carrental.model.dto.CarDto;
import com.github.carrental.model.dto.RentDto;
import com.github.carrental.service.CarService;
import com.github.carrental.service.RentService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final RentService rentService;

    @PostMapping("/save")
    private void saveCar(@RequestBody CarDto dto) {
        carService.saveCar(dto);
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
    private Map<String, CarDto> getAllRentedCars() {
        return carService.getAllRented();
    }

    @PutMapping("/delete")
    private ResponseEntity<?> deleteCar(@RequestParam Boolean temporary,
                                        @RequestParam("car_reg_num") String carRegNum) {
        if (temporary) {
            carService.markUnavailable(carRegNum);
        } else {
            carService.deleteCar(carRegNum);
        }
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/to-rent")
    private ResponseEntity<?> toRent(@RequestBody RentDto dto) {
        rentService.toRent(dto);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/receive")
    private ResponseEntity<?> receiveCar(@RequestBody RentDto dto) {
        rentService.receiveCar(dto);
        return ResponseEntity.ok("SUCCESS");
    }

}
