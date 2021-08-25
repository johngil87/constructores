package com.sura.constructores.constructor.mapper;

import com.sura.constructores.constructor.DTOs.EstadoProyectoDTO;
import com.sura.constructores.constructor.entities.ProjectStatusEntity;

public class ProyectoMap {

    public EstadoProyectoDTO entityToDto(ProjectStatusEntity entity){
        return EstadoProyectoDTO.builder()
                .fechaFinal(entity.getFinishDate())
                .nombreProyecto(entity.getNameProject())
                .obrasTotales(entity.getTotalWorks())
                .build();
    }

    public ProjectStatusEntity dtoToEntity(EstadoProyectoDTO dto){
        return ProjectStatusEntity.builder()
                .finishDate(dto.getFechaFinal())
                .nameProject(dto.getNombreProyecto())
                .totalWorks(dto.getObrasTotales())
                .build();
    }
}
