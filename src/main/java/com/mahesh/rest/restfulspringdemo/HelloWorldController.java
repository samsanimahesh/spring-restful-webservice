package com.mahesh.rest.restfulspringdemo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource source;
	
	@RequestMapping(method=RequestMethod.GET,path="/helloWorld")
	public String helloWorld(){
		return "Hello World..!";
	}
	
	@GetMapping(path="/hello-world-bean/{message}")
	public HelloWorldBean helloWorldBean(@PathVariable String message){
		return new HelloWorldBean(message);
	}
	
	@GetMapping(path="/hello-world-bean-internationalized")
//	public String helloWorldBeanInternationalized(@RequestHeader(name="Accept-Language",required=false) Locale locale){
	public String helloWorldBeanInternationalized(){
		return source.getMessage("good.morning.message",null,LocaleContextHolder.getLocale());
	}
	

}
