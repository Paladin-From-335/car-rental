package com.github.carrental.service.impl;

import com.github.carrental.model.dto.RentDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.model.entity.Rent;
import com.github.carrental.model.entity.User;
import com.github.carrental.repo.CarRepo;
import com.github.carrental.repo.RentRepo;
import com.github.carrental.repo.UserRepo;
import com.github.carrental.service.RentService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepo rentRepo;
    private final UserRepo userRepo;
    private final CarRepo carRepo;

    @Override
    public void toRent(RentDto dto) {
        Rent rent = checkUserAndCar(dto);
        rentRepo.save(rent);
    }

    @Override
    public void receiveCar(RentDto dto) {
        rentRepo.deleteByDriverLicense(dto.getDriverLicense());
    }

    private Rent checkUserAndCar(RentDto dto) {
        Optional<User> userId = userRepo.findByLicense(dto.getDriverLicense());
        Optional<Car> carId = carRepo.findByCarRegNumber(dto.getCarRegNumber());
        return new Rent(
                userId.orElseThrow(),
                carId.orElseThrow()
        );
    }
}
