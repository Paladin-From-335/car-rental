package com.github.carrental.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {

    @JsonProperty("car_reg_number")
    private String carRegNumber;

    @JsonProperty("car_brand")
    private String carBrand;

    @JsonProperty("tmp_unavailable")
    private Boolean tmpUnavailable = false;
}
