package com.bobst.knowledge;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bobst.knowledge.repository.KpiRepository;

@SpringBootApplication
public class KnowledgeApplication {
	
	@Autowired
	KpiRepository repository;	

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(KnowledgeApplication.class, args);
	}

}
