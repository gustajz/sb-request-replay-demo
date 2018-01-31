package com.github.gustajz.requestreply.client;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.github.gustajz.requestreply.CalculationRequest;
import com.github.gustajz.requestreply.CalculationResponse;

@MessagingGateway
public interface CalculatorGateway {

	final String REQUEST = "multiply-request";
	final String REPLAY = "multiply-replay";

	@Gateway(requestChannel = REQUEST, replyChannel = REPLAY, replyTimeout = 5000)
	CalculationResponse multiply(CalculationRequest request);
	
}
