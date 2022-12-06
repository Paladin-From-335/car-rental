package com.github.carrental.service;

import com.github.carrental.model.dto.RentDto;

public interface RentService {

    RentDto toRent(RentDto dto);

    void receiveCar(String carRegNumber);
}
