package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ConstructionReportDTO {

    private int casas;
    private int lagos;
    private int canchas;
    private int edificios;
    private int gimnasios;
}
