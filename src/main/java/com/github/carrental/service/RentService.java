package com.github.carrental.service;

import com.github.carrental.model.dto.RentDto;

public interface RentService {

    void toRent(RentDto dto);

    void receiveCar(RentDto dto);
}
