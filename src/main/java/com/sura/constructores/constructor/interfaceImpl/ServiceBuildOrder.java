package com.sura.constructores.constructor.interfaceImpl;

import com.sura.constructores.constructor.DTOs.MaterialDTO;
import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.ReportDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;

import java.io.IOException;
import java.text.ParseException;

public interface ServiceBuildOrder {

    RespuestaDTO createOrder(OrdenConstruccionDTO dto) throws ParseException;
    RespuestaDTO validateOrder(OrdenConstruccionDTO dto);
    RespuestaDTO getFinishDate();
    ReportDTO getReport() throws IOException;
    RespuestaDTO loadResources(MaterialDTO dto);
}
