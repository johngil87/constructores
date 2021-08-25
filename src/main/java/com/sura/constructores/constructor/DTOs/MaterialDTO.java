package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MaterialDTO {

    private String idMaterial;
    private String tipoMaterial;
    private Double CantidadMaterial;

}
