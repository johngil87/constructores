package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConstruccionDTO {

    private String idConstruccion;
    private String tipoConstruccion;
    private Double cantidadCemento;
    private Double cantidadGrava;
    private Double cantidadArena;
    private Double cantidadMadera;
    private Double cantidadAdobe;
    private Integer dias;
}
