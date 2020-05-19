package io.abhi.springbootconfig.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.springbootconfig.configuration.DBConfig;

@RestController
@RefreshScope
public class GreetingController {
	
	@Value("${my.greetings}")
	private String greeting;
	
	
	@Value("${default: Default Value}") // Default Message
	private String defaultValue;
	
	
	@Value("static value Value")		// Static Value
	private String staticValue;
	
	
	@Value("${my.list.Values}")
	private List<String> Values;  	///LISTS
	
//	@Value("#{${dbValues}}")         	// MAP
//	private Map<String, String> dbValues;
	
	@Autowired
	private DBConfig dbConfig;
	
	@Autowired
	private Environment environment;

	@GetMapping("/getGreeting")
	public String getGreeting() {
		return greeting  + "  " +Values  + " "+defaultValue + " "  ;
	}
	
	
	@GetMapping("/getDbValues")
	public String getDbValues() {
		return dbConfig.getHost() + " " +dbConfig.getName() + " " +dbConfig.getPort() + " "+ dbConfig.getValues() ;
	}
	
	
	@GetMapping("/getEnvDetails")
	public String envDetails() {
		return environment.toString();
	}
}
