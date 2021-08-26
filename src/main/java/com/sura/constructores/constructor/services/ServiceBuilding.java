package com.sura.constructores.constructor.services;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;
import com.sura.constructores.constructor.entities.BuildOrderEntity;
import com.sura.constructores.constructor.helpers.CreateDate;
import com.sura.constructores.constructor.mapper.OrderMap;
import com.sura.constructores.constructor.repositories.BuildOrderRepository;
import com.sura.constructores.constructor.repositories.BuildingRepository;
import com.sura.constructores.constructor.repositories.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBuilding {

    @Autowired
    private BuildOrderRepository buildOrderRepository;
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private OrderMap orderMapper;
    @Autowired
    private CreateDate createDate;


    public RespuestaDTO createOrder(OrdenConstruccionDTO dto){
        var entity =orderMapper.dtoToEntity(dto);

        Integer days = buildingRepository.findByTypeBuilding(dto.getTipo()).getDays();
        var project = projectStatusRepository.findAll().get(0);
        entity.setStartDate(createDate.setStartDate(project.getFinishDate()));
        entity.setFinishDate(createDate.setFinishDate(entity.getStartDate(), days));
        return setResponse(entity);
    }

    private Boolean validateCoordinates(Double x, Double y){
        var build = buildOrderRepository.findByCoordinateXAndCoordinateY(x, y);
        var validate = build.isEmpty();
        return !validate;
    }

    private RespuestaDTO setResponse(BuildOrderEntity entity){
        if(validateCoordinates(entity.getCoordinateX(), entity.getCoordinateY())){
            entity.setStatus("pendiente");
            buildOrderRepository.save(entity);
            return new RespuestaDTO(
                   "fecha finalizacion del proyecto "+entity.getFinishDate().toString(),
                    "la orden fue guardada con exito"
            );
        }
        return new RespuestaDTO(
                "fecha finalizacion del proyecto "+entity.getFinishDate().toString(),
                "la solicitud no se puede realizar"
        );
    }

}
