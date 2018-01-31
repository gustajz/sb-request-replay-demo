package com.github.gustajz.requestreply.server;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public CalculationResponse multiply(final CalculationRequest request) {
		return CalculationResponse.builder()
				.request(request)
				.result(request.getNumberA() * request.getNumberB())
			.build();
	}

}
