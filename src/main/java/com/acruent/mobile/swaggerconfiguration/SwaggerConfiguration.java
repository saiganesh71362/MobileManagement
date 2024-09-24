package com.acruent.mobile.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("MobileMangement")
	                .packagesToScan("com.acruent.mobile.controller") // Specify the package to scan
	                .build();
	 }

}
