package com.sura.constructores.constructor.mapper;

import com.sura.constructores.constructor.DTOs.ConstruccionDTO;
import com.sura.constructores.constructor.entities.BuildingEntity;

public class ConstruccionMap {

    public ConstruccionDTO entityToDto(BuildingEntity entity){
        return ConstruccionDTO.builder()
                .idConstruccion(entity.getId())
                .tipoConstruccion(entity.getTypeBuilding())
                .cantidadCemento(entity.getAmountCement())
                .cantidadGrava(entity.getAmountGravel())
                .cantidadArena(entity.getAmountSand())
                .cantidadMadera(entity.getAmountWood())
                .cantidadAdobe(entity.getAmountAdobe())
                .dias(entity.getDays())
                .build();
    }

    public BuildingEntity dtoToEntity(ConstruccionDTO dto){
        return BuildingEntity.builder()
                .id(dto.getIdConstruccion())
                .typeBuilding(dto.getTipoConstruccion())
                .amountCement(dto.getCantidadCemento())
                .amountGravel(dto.getCantidadGrava())
                .amountSand(dto.getCantidadArena())
                .amountWood(dto.getCantidadMadera())
                .amountAdobe(dto.getCantidadAdobe())
                .days(dto.getDias())
                .build();
    }

}
