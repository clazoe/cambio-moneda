package com.bcp.api.reactive.cambiomoneda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.api.reactive.cambiomoneda.dto.MonedaConvertidorDTO;
import com.bcp.api.reactive.cambiomoneda.repository.MonedaConvertidorRepository;
import com.bcp.api.reactive.cambiomoneda.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MonedaConvertidorService {
	
	@Autowired
    private MonedaConvertidorRepository repository;
	
	public Flux<MonedaConvertidorDTO> getTiposDeCambio(){
	        return repository.findAll().map(AppUtils::entityToDto);
	    }
	
	public Mono<MonedaConvertidorDTO> getTipodeCambio(String monedaOrigen, String monedaDestino){
        return repository.getTipodeCambio(monedaOrigen, monedaDestino);
    }
	
	public Mono<MonedaConvertidorDTO> saveTipoDeCambio(Mono<MonedaConvertidorDTO> monedaConvertidorDTOMono){
        System.out.println("service method called ...");
      return  monedaConvertidorDTOMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<MonedaConvertidorDTO> updateTipoDeCambio(Mono<MonedaConvertidorDTO> monedaConvertidorDTOMono,String id){
       return repository.findById(id)
                .flatMap(p->monedaConvertidorDTOMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);

    }

}
