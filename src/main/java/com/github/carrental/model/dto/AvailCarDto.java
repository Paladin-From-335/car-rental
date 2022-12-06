package com.github.carrental.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailCarDto {

    @JsonProperty("is_unavailable")
    private Boolean isUnavailable;

    @JsonProperty("car_reg_number")
    private String carRegNumber;
}
