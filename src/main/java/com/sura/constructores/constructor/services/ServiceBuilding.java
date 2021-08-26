package com.sura.constructores.constructor.services;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;
import com.sura.constructores.constructor.helpers.CreateDate;
import com.sura.constructores.constructor.mapper.OrderMap;
import com.sura.constructores.constructor.repositories.BuildOrderRepository;
import com.sura.constructores.constructor.repositories.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceBuilding {

    @Autowired
    private BuildOrderRepository buildOrderRepository;
    @Autowired
    private OrderMap orderMapper;
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Autowired
    private CreateDate createDate;


    public RespuestaDTO createOrder(OrdenConstruccionDTO dto){
        var entity =orderMapper.dtoToEntity(dto);
        var project = projectStatusRepository.findAll().get(0);
        entity.setStartDate(setDate(project.getFinishDate()));
        entity.getFinishDate();
        return new RespuestaDTO();

    }



    private Date setDate(Date date){
        if(date == null){
            return createDate.getActualDate();
        }
        return createDate.setStartDate(date);
    }

}
