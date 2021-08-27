package com.bcp.api.reactive.cambiomoneda.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "currencies")
public class MonedaConvertidor {
	
	@Id
    private String id;
	private double monto;
	private String monedaOrigen;
	private String monedaDestino;
	private double montoConTipoDeCambio;
	private double tipoDeCambio;

}
