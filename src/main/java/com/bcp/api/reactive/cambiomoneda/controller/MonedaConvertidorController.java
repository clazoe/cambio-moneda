package com.bcp.api.reactive.cambiomoneda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.api.reactive.cambiomoneda.dto.MonedaConvertidorDTO;
import com.bcp.api.reactive.cambiomoneda.service.MonedaConvertidorService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/currencies")
public class MonedaConvertidorController {

	@Autowired
    private MonedaConvertidorService service;


	@GetMapping
    public Flux<MonedaConvertidorDTO> getTiposDeCambio(){
        return service.getTiposDeCambio();
    }


	@GetMapping("/tipo-cambio")
    public Mono<MonedaConvertidorDTO> getTipodeCambio(@RequestParam("monto") double monto, @RequestParam("monedaOrigen") String monedaOrigen, @RequestParam("monedaDestino") String monedaDestino){
        return service.getTipodeCambio(monedaOrigen,monedaDestino).map(monedaConvertidorDTO -> {
      	      monedaConvertidorDTO.setMonto(monto);
        	  monedaConvertidorDTO.setMontoConTipoDeCambio(monto*monedaConvertidorDTO.getTipoDeCambio()); 
              return monedaConvertidorDTO;                
          });
    }

	@PostMapping
    public Mono<MonedaConvertidorDTO> saveTipoDeCambio(@RequestBody Mono<MonedaConvertidorDTO> monedaConvertidorDTO){
        System.out.println("controller method called ...");
        return service.saveTipoDeCambio(monedaConvertidorDTO);
    }

	@PutMapping("/update/{id}")
    public Mono<MonedaConvertidorDTO> updateTipoDeCambio(@RequestBody Mono<MonedaConvertidorDTO> monedaConvertidorDTOMono,@PathVariable String id){
        return service.updateTipoDeCambio(monedaConvertidorDTOMono,id);
    }

}
