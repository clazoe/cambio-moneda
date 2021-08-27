package com.bcp.api.reactive.cambiomoneda.utils;

import com.bcp.api.reactive.cambiomoneda.dto.MonedaConvertidorDTO;
import com.bcp.api.reactive.cambiomoneda.entity.MonedaConvertidor;
import org.springframework.beans.BeanUtils;

public class AppUtils {
    
    
    public static MonedaConvertidorDTO entityToDto(MonedaConvertidor monedaConvertidor) {
    	MonedaConvertidorDTO monedaConvertidorDTO = new MonedaConvertidorDTO();
        BeanUtils.copyProperties(monedaConvertidor, monedaConvertidorDTO);
        return monedaConvertidorDTO;
    }

    public static MonedaConvertidor dtoToEntity(MonedaConvertidorDTO monedaConvertidorDTO) {
    	MonedaConvertidor monedaConvertidor = new MonedaConvertidor();
        BeanUtils.copyProperties(monedaConvertidorDTO, monedaConvertidor);
        return monedaConvertidor;
    }
}
