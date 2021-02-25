package com.bobst.knowledge;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.bobst.knowledge.models.KPI_USER;
import com.bobst.knowledge.repository.KpiRepository;
@SpringBootApplication
public class KnowledgeApplication {
	
	@Autowired
	KpiRepository repository;
	

	public static void main(String[] args) throws IOException, URISyntaxException {
		//SpringApplication.run(KnowledgeApplication.class, args);
		
		new SpringApplicationBuilder(KnowledgeApplication.class).headless(false).run(args);
		//openBrowserAfterStartup();
		
	}
	
	 //@EventListener(ApplicationReadyEvent.class)
	    public static void openBrowserAfterStartup() throws IOException, URISyntaxException {
	        // open default browser after start:
	        Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
	    }
	

}
