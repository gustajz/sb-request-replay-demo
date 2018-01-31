package com.github.gustajz.requestreply.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculationRequest {

	private Integer numberA;
	
	private Integer numberB;
	
}