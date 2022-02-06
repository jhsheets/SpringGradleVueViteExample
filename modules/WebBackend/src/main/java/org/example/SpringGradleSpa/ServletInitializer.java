package org.example.SpringGradleSpa;

import org.example.ServiceConstants;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringGradleSpaApplication.class);
	}

	@RestController
	@RequestMapping("/api")
	public static class RestTest
	{
		@GetMapping(value = "/service/version", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity serviceVersion() {
			HashMap<String, String> map = new HashMap<>();
			map.put("version", ServiceConstants.VERSION);
			return ResponseEntity.ok(map);
		}
	}
}
