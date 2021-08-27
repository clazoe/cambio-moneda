package com.bcp.api.reactive.cambiomoneda.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bcp.api.reactive.cambiomoneda.dto.MonedaConvertidorDTO;
import com.bcp.api.reactive.cambiomoneda.entity.MonedaConvertidor;

import reactor.core.publisher.Mono;

@Repository
public interface MonedaConvertidorRepository extends ReactiveMongoRepository<MonedaConvertidor,String>{
	
	@Query("{monedaOrigen: ?0, monedaDestino: ?1}")
    Mono<MonedaConvertidorDTO> getTipodeCambio(String monedaOrigen, String monedaDestino);

}
