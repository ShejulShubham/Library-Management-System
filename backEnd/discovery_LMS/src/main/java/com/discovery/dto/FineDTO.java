package com.discovery.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineDTO {
    private Long id;
    private Long borrowId;
    private Long userId;
    private double fineAmount;
    private LocalDate fineDate;
    private boolean paid;
}
