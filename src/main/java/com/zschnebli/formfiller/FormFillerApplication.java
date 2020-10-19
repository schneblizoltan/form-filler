package com.zschnebli.formfiller;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormFillerApplication {

	public static void main(String[] args) throws IOException
	{
		SpringApplication.run(FormFillerApplication.class, args);

		Runtime rt = Runtime.getRuntime();
		String url = "http://localhost:8080/form";
		rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
	}
}
