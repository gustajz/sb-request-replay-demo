package com.github.gustajz.requestreplay.client;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.github.gustajz.requestreplay.CalculationRequest;
import com.github.gustajz.requestreplay.CalculationResponse;

@MessagingGateway
public interface CalculatorGateway {

	final String REQUEST = "multiply-request";
	final String REPLAY = "multiply-replay";

	@Gateway(requestChannel = REQUEST, replyChannel = REPLAY, replyTimeout = 5000)
	CalculationResponse multiply(CalculationRequest request);
	
}
