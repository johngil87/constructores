package com.sura.constructores.constructor.services;

import com.sura.constructores.constructor.DTOs.*;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public RespuestaDTO createOrder(OrdenConstruccionDTO dto) throws ParseException {
        var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var entity = orderMapper.dtoToEntity(dto);
        Integer days = buildingRepository.findByTypeBuilding(dto.getTipo()).getDays();
        var project = projectStatusRepository.findAll().get(0);
        entity.setStartDate(simpleDateFormat.format(createDate.setStartDate(project.getFinishDate())));
        entity.setFinishDate(simpleDateFormat.format(createDate.setFinishDate(simpleDateFormat.parse(entity.getStartDate()), days)));
        project.setFinishDate(simpleDateFormat.parse(entity.getFinishDate()));
        return saveOrder(entity, project, dto);
    }

    @Override
    public RespuestaDTO validateOrder(OrdenConstruccionDTO dto) {
        if (!validateCoordinates(dto.getCoordenadasX(), dto.getCoordenadasY()) && validaMaterial(dto)) {
            return RespuestaDTO.builder()
                    .respuesta("La orden puede ser creada de manera satisfactoria")
                    .build();
        }
        return RespuestaDTO.builder()
                .respuesta("La orden no puede ser creada")
                .build();
    }

    @Override
    public RespuestaDTO getFinishDate() {
        var entity = projectStatusRepository.findAll().get(0);
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        return RespuestaDTO.builder()
                .fecha("fecha de finalizacion del proyecto " + formatDate.format(entity.getFinishDate()))
                .nombreProyecto(entity.getNameProject())
                .build();
    }

    @Override
    public ReportDTO getReport() {
        var finish = constructionReport(buildOrderRepository.findAllByStatus("terminado"));
        var progress = constructionReport(buildOrderRepository.findAllByStatus("progreso"));
        var pending = constructionReport(buildOrderRepository.findAllByStatus("pendiente"));
        var entity = projectStatusRepository.findAll().get(0);
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        return new ReportDTO(
                entity.getNameProject(),
                entity.getFinishDate().toString(),
                "reporte de obra",
                "Obras pendientes:" + " casas " + pending.getCasas() + " lagos " + pending.getLagos() + " canchas de futbol " + pending.getCanchas() + " edificios " + pending.getEdificios() + " gimnasios " + pending.getGimnasios(),
                "Obras terminadas: " + " casas " + finish.getCasas() + " lagos " + finish.getLagos() + " canchas de futbol " + finish.getCanchas() + " edificios " + finish.getEdificios() + " gimnasios " + finish.getGimnasios() ,
                "Obras en progreso: " + " casas " + progress.getCasas() + " lagos " + progress.getLagos() + " canchas de futbol " + progress.getCanchas() + " edificios " + progress.getEdificios() + " gimnasios " + progress.getGimnasios()
        );
    }

    @Override
    public RespuestaDTO loadResources(MaterialDTO dto) {
        var material = getMaterial(dto.getTipoMaterial());
        var amount = material.getAmount() + dto.getCantidadMaterial();
        material.setAmount(amount);
        materialRepository.save(material);
        return RespuestaDTO.builder()
                .respuesta("el material fue guardado con exito")
                .build();
    }

    private ConstructionReportDTO constructionReport(List<BuildOrderEntity> list) {
        var casa = countConstructions(list, "casa");
        var lago = countConstructions(list, "lago");
        var cancha = countConstructions(list, "cancha de futbol");
        var edificio = countConstructions(list, "edificio");
        var gym = countConstructions(list, "gimnasio");
        return ConstructionReportDTO.builder()
                .casas((int) casa)
                .lagos((int) lago)
                .canchas((int) cancha)
                .edificios((int) edificio)
                .gimnasios((int) gym)
                .build();
    }

    private long countConstructions(List<BuildOrderEntity> list, String build) {
        return list.stream().filter(it -> it.getType().equals(build)).count();
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
                    .respuesta("La orden fue creada satisfactoriamente")
                    .build();
        }
        return RespuestaDTO.builder()
                .respuesta("La orden no pudo ser creada verifica cantidad de materiales o coordenadas")
                .build();
    }

    private Boolean validaMaterial(OrdenConstruccionDTO dto) {
        var build = buildingRepository.findByTypeBuilding(dto.getTipo());
        return materialResource(build.getAmountCement(), getMaterial("cement").getAmount()) &&
                materialResource(build.getAmountGravel(), getMaterial("gravel").getAmount()) &&
                materialResource(build.getAmountSand(), getMaterial("sand").getAmount()) &&
                materialResource(build.getAmountWood(), getMaterial("wood").getAmount()) &&
                materialResource(build.getAmountAdobe(), getMaterial("adobe").getAmount());
    }

    private Boolean materialResource(Double required, Double resource) {
        if (required > resource) {
            return false;
        }
        return true;
    }

    private void discountResource(String type) {
        var build = buildingRepository.findByTypeBuilding(type);
        resource("cement", build.getAmountCement());
        resource("adobe", build.getAmountAdobe());
        resource("gravel", build.getAmountGravel());
        resource("sand", build.getAmountSand());
        resource("wood", build.getAmountWood());
    }

    private MaterialEntity resource(String material, Double amount) {
        var resource = getMaterial(material);
        resource.setAmount(resource.getAmount() - amount);
        return materialRepository.save(resource);
    }

    private MaterialEntity getMaterial(String type) {
        return materialRepository.findByType(type);
    }

    @Scheduled(cron = "0 0 7 * * *")
    private void verifyStartPendingConstruction() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var date = simpleDateFormat.format(new Date());
        var order = buildOrderRepository.findByStartDateStartsWith(date);
        if (order != null && order.getStatus().equals("pendiente")) {
            order.setStatus("en progreso");
            buildOrderRepository.save(order);
        }
    }

    @Scheduled(cron = "0 0 20 * * *")
    private void verifyFinishedConstruction() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var date = simpleDateFormat.format(new Date());
        var order = buildOrderRepository.findByFinishDateStartsWith(date);
        if (order != null && order.getStatus().equals("en progreso")) {
            order.setStatus("terminado");
            buildOrderRepository.save(order);
        }
    }

}
