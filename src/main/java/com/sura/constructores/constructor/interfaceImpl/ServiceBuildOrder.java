package com.sura.constructores.constructor.interfaceImpl;

import com.sura.constructores.constructor.DTOs.MaterialDTO;
import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.ReportDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;

public interface ServiceBuildOrder {

    RespuestaDTO createOrder(OrdenConstruccionDTO dto);
    RespuestaDTO validateOrder(OrdenConstruccionDTO dto);
    RespuestaDTO getFinishDate();
    ReportDTO getReport();
    RespuestaDTO loadResources(MaterialDTO dto);
}
