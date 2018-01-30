package com.github.gustajz.requestreplay.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.github.gustajz.requestreplay.server.CalculatorServerConfig.CalculatorChannel;

@Service
public class CalculatorService {

	@Autowired
	private Calculator calculator;
	
	@StreamListener(CalculatorChannel.INPUT)
	@SendTo(CalculatorChannel.OUTPUT)
	public Message<CalculationResponse> calculate(Message<CalculationRequest> request) {
		CalculationResponse response = calculator.multiply(request.getPayload());
		
		Message<CalculationResponse> message = MessageBuilder
				.withPayload(response)
				.copyHeadersIfAbsent(request.getHeaders())
				.build();
		
		return message;
	}
}
