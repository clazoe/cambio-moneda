package com.bcp.api.reactive.cambiomoneda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonedaConvertidorDTO {
	
	private String id;
	private double monto;
	private String monedaOrigen;
	private String monedaDestino;
	private double montoConTipoDeCambio;
	private double tipoDeCambio;

}
