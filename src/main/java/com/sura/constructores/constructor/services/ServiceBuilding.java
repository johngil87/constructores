package com.sura.constructores.constructor.services;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;
import com.sura.constructores.constructor.entities.BuildOrderEntity;
import com.sura.constructores.constructor.entities.MaterialEntity;
import com.sura.constructores.constructor.entities.ProjectStatusEntity;
import com.sura.constructores.constructor.helpers.CreateDate;
import com.sura.constructores.constructor.interfaceImpl.ServiceBuildOrder;
import com.sura.constructores.constructor.mapper.OrderMap;
import com.sura.constructores.constructor.repositories.BuildOrderRepository;
import com.sura.constructores.constructor.repositories.BuildingRepository;
import com.sura.constructores.constructor.repositories.MaterialRepository;
import com.sura.constructores.constructor.repositories.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;

@Service
public class ServiceBuilding implements ServiceBuildOrder {

    @Autowired
    private BuildOrderRepository buildOrderRepository;
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private OrderMap orderMapper;
    @Autowired
    private CreateDate createDate;

    @Override
    public RespuestaDTO createOrder(OrdenConstruccionDTO dto) {
        var entity = orderMapper.dtoToEntity(dto);
        Integer days = buildingRepository.findByTypeBuilding(dto.getTipo()).getDays();
        var project = projectStatusRepository.findAll().get(0);
        entity.setStartDate(createDate.setStartDate(project.getFinishDate()));
        entity.setFinishDate(createDate.setFinishDate(entity.getStartDate(), days));
        project.setFinishDate(entity.getFinishDate());
        return saveOrder(entity, project, dto);
    }

    @Override
    public RespuestaDTO validateOrder(OrdenConstruccionDTO dto) {
        if(!validateCoordinates(dto.getCoordenadasX(), dto.getCoordenadasY()) && validaMaterial(dto)){
            return RespuestaDTO.builder()
                    .respuesta("La orden puede ser creada de manera satisfactoria")
                    .build();
        }
        return RespuestaDTO.builder()
                .respuesta("La orden no puede ser creada")
                .build();
    }

    public RespuestaDTO getFinishDate(){
        var entity = projectStatusRepository.findAll().get(0);
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        return RespuestaDTO.builder()
                .fecha("fecha de finalizacion del proyecto "+formatDate.format(entity.getFinishDate()))
                .nombreProyecto(entity.getNameProject())
                .build();
    }


    private Boolean validateCoordinates(Double x, Double y) {
        var build = buildOrderRepository.findByCoordinateXAndCoordinateY(x, y);
        var validate = build.isEmpty();
        return !validate;
    }

    private RespuestaDTO saveOrder(BuildOrderEntity entity, ProjectStatusEntity project, OrdenConstruccionDTO dto) {
        if (!validateCoordinates(entity.getCoordinateX(), entity.getCoordinateY()) && validaMaterial(dto)) {
            entity.setStatus("pendiente");
            buildOrderRepository.save(entity);
            projectStatusRepository.save(project);
            discountResource(entity.getType());
            return RespuestaDTO.builder()
                    .respuesta("La orden creada satisfactoriamente")
                    .build();
        }
        return RespuestaDTO.builder()
                .respuesta("La orden no pudo ser creada")
                .build();
    }

    private Boolean validaMaterial(OrdenConstruccionDTO dto) {
        var build = buildingRepository.findByTypeBuilding(dto.getTipo());
        return materialResource(build.getAmountCement(), materialRepository.findByType("cement").getAmount()) &&
                materialResource(build.getAmountGravel(), materialRepository.findByType("gravel").getAmount()) &&
                materialResource(build.getAmountSand(), materialRepository.findByType("sand").getAmount()) &&
                materialResource(build.getAmountWood(), materialRepository.findByType("wood").getAmount())&&
                materialResource(build.getAmountAdobe(), materialRepository.findByType("adobe").getAmount());
    }

    private Boolean materialResource(Double required, Double resource){
        if(required > resource){
            return false;
        }
        return true;
    }

    private void discountResource(String type){
        var build = buildingRepository.findByTypeBuilding(type);
        resource("cement", build.getAmountCement());
        resource("adobe", build.getAmountAdobe());
        resource("gravel", build.getAmountGravel());
        resource("sand", build.getAmountSand());
        resource("wood", build.getAmountWood());
    }

    private MaterialEntity resource(String material , Double amount){
        var resource= materialRepository.findByType(material);
        resource.setAmount(resource.getAmount()- amount);
        return  materialRepository.save(resource);
    }

}
