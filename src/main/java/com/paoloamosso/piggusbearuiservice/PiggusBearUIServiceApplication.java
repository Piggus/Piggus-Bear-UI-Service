package com.paoloamosso.piggusbearuiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@SpringBootApplication
@EnableEurekaClient
@Controller
public class PiggusBearUIServiceApplication {

	@RequestMapping("/")
	public ModelAndView home(Principal user) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("user",user.getName());
		return modelAndView;
	}

	@Autowired
	OAuth2RestTemplate restTemplate;

	@RequestMapping("/ciao")
	@ResponseBody
	public String ciao(Principal user) {
		ResponseEntity responseEntity = restTemplate.getForEntity("http://localhost:8080/security/whoami",String.class);
		return "Ciao " + user.getName() + responseEntity.getBody(); }

	public static void main(String[] args) {
		SpringApplication.run(PiggusBearUIServiceApplication.class, args);
	}
}

