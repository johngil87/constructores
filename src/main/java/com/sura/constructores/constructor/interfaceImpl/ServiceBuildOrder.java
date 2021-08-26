package com.sura.constructores.constructor.interfaceImpl;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.DTOs.RespuestaDTO;

public interface ServiceBuildOrder {

    RespuestaDTO createOrder(OrdenConstruccionDTO dto);
    RespuestaDTO validateOrder(OrdenConstruccionDTO dto);
}
