package com.booking_app.backend.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessServiceRequest {
    
    private String name;

    private String description;

    private Integer durationMinutes;

    private BigDecimal price;
}
