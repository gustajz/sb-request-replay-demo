package com.github.gustajz.requestreply.client;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gustajz.requestreply.CalculationRequest;
import com.github.gustajz.requestreply.CalculationResponse;
import com.github.gustajz.requestreply.Calculator;

@RestController
public class DemoController {

	@Autowired
	CalculatorGateway gateway;
	
	@Autowired
	Calculator calculator;
	
	@GetMapping
	public CalculationResponse get() {
		Random random = new Random();
		
		CalculationRequest request = CalculationRequest.builder().numberA(random.nextInt(1000)).numberB(random.nextInt(1000)).build();

		CalculationResponse response = gateway.multiply(request);
		
		CalculationResponse responseCheck = calculator.multiply(request);
		
		// Just for demo
		assert response.getResult() == responseCheck.getResult(); 
		
		return response;
	}
}
