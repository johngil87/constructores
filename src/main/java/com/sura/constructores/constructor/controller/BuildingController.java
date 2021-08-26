package com.sura.constructores.constructor.controller;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;
import com.sura.constructores.constructor.services.ServiceBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BuildingController {

    @Autowired
    private ServiceBuilding serviceBuilding;

    @PostMapping("/crearorden")
    public ResponseEntity<RespuestaDTO> createOrder(@RequestBody OrdenConstruccionDTO dto){
        return new ResponseEntity<>(serviceBuilding.createOrder(dto), HttpStatus.OK);
    }

}
