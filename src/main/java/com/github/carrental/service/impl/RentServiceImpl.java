package com.github.carrental.service.impl;

import com.github.carrental.exception.NotExistException;
import com.github.carrental.model.dto.RentDto;
import com.github.carrental.model.entity.Car;
import com.github.carrental.model.entity.Rent;
import com.github.carrental.model.entity.User;
import com.github.carrental.repo.CarRepo;
import com.github.carrental.repo.RentRepo;
import com.github.carrental.repo.UserRepo;
import com.github.carrental.service.RentService;
import com.github.carrental.util.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepo rentRepo;
    private final UserRepo userRepo;
    private final CarRepo carRepo;
    private final Mapper mapper;

    @Override
    public RentDto toRent(RentDto dto) {
        Rent rent = checkUserAndCar(dto);
        return mapper.entityToDto(rentRepo.save(rent));
    }

    @Override
    public void receiveCar(String carRegNumber) {
        if (rentRepo.findByCarRegNumber(carRegNumber).isEmpty()) {
            throw new NotExistException("This car isn't rented");
        }
        rentRepo.deleteByCarNumber(carRegNumber);
    }

    private Rent checkUserAndCar(RentDto dto) {
        try {
            Optional<User> user = userRepo.findByLicense(dto.getDriverLicense());
            Optional<Car> car = carRepo.findByCarRegNumber(dto.getCarRegNumber());
            if (user.isPresent() && car.isPresent() && !car.get().getTmpUnavailable()) {
                return new Rent(
                        user.get().getDriverLicense(),
                        car.get().getCarRegNumber()
                );
            }
        } catch (Exception e) {
            throw new NotExistException("Current car or user is not exist");
        }
        throw new NotExistException("Current car or user is not exist");
    }
}
