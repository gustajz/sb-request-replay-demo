package com.github.gustajz.requestreply.server;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import com.github.gustajz.requestreply.server.CalculatorServerConfig.CalculatorChannel;

@Configuration
@EnableBinding(CalculatorChannel.class)
public class CalculatorServerConfig {

	interface CalculatorChannel {
		String OUTPUT = "multiply-output";
		String INPUT = "multiply-input";

		@Output(OUTPUT)
		MessageChannel output();

		@Input(INPUT)
		SubscribableChannel input();
	}

}
