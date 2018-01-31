package com.github.gustajz.requestreply.client;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.HeaderEnricherSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gustajz.requestreply.CalculationResponse;
import com.github.gustajz.requestreply.client.CalculatorConfig.CalculatorChannel;

@Configuration
@EnableBinding(CalculatorChannel.class)
@EnableIntegration
public class CalculatorConfig {

	@Bean
	public IntegrationFlow multiplyOutputFlow() {
		return IntegrationFlows
				.from(CalculatorGateway.REQUEST)
				.enrichHeaders(HeaderEnricherSpec::headerChannelsToString)
				.channel(CalculatorChannel.OUTPUT)
				.get();
	}

	@Bean
	public IntegrationFlow multiplyInputFlow(Jackson2JsonObjectMapper mapper) {
		return IntegrationFlows
				.from(CalculatorChannel.INPUT)
				.transform(Transformers.fromJson(CalculationResponse.class, mapper))
				.channel(CalculatorGateway.REPLAY)
				.get();
	}

	@Bean
	public Jackson2JsonObjectMapper jackson2JsonObjectMapper(ObjectMapper mapper) {
		return new Jackson2JsonObjectMapper(mapper);
	}


	interface CalculatorChannel {
		String OUTPUT = "multiply-output";
		String INPUT = "multiply-input";

		@Output(OUTPUT)
		MessageChannel output();

		@Input(INPUT)
		SubscribableChannel input();
	}
}
