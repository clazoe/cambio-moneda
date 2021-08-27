package com.bcp.api.reactive.cambiomoneda;

import  static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bcp.api.reactive.cambiomoneda.controller.MonedaConvertidorController;
import com.bcp.api.reactive.cambiomoneda.dto.MonedaConvertidorDTO;
import com.bcp.api.reactive.cambiomoneda.service.MonedaConvertidorService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(MonedaConvertidorController.class)
class CambioMonedaApplicationTests {

	  @Autowired
	    private WebTestClient webTestClient;
	    @MockBean
	    private MonedaConvertidorService service;

	    @Test
	    public void addTipoCambioTest(){
			Mono<MonedaConvertidorDTO> monedaConvertidorDTOMono=Mono.just(new MonedaConvertidorDTO("1000", 0.0,"MNA","USD",0.0,0.25));
			when(service.saveTipoDeCambio(monedaConvertidorDTOMono)).thenReturn(monedaConvertidorDTOMono);

			webTestClient.post().uri("/currencies")
					.body(Mono.just(monedaConvertidorDTOMono),MonedaConvertidorDTO.class)
					.exchange()
					.expectStatus().isOk();//200

		}



}
