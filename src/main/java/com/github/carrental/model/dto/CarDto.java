package com.github.carrental.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {

    @JsonProperty("car_reg_number")
    private String carRegNumber;

    @JsonProperty("car_brand")
    private String carBrand;
}
