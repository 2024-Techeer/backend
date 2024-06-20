package com.example.Backend;

import com.example.Backend.domain.common.entities.Position;
import com.example.Backend.domain.common.entities.TechStack;
import com.example.Backend.domain.common.repositories.PositionRepository;
import com.example.Backend.domain.common.repositories.TechStackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PositionRepository positionRepository, TechStackRepository techStackRepository) {
		return args -> {
			if (positionRepository.count() == 0) {
				Position backend = new Position();
				backend.setName("backend");
				Position frontend = new Position();
				frontend.setName("frontend");
				Position mobile = new Position();
				mobile.setName("mobile");
				Position etc = new Position();
				etc.setName("etc");

				backend = positionRepository.save(backend);
				frontend = positionRepository.save(frontend);
				mobile = positionRepository.save(mobile);
				etc = positionRepository.save(etc);

				techStackRepository.save(new TechStack(null, backend, "Spring"));
				techStackRepository.save(new TechStack(null, backend, "Nodejs"));
				techStackRepository.save(new TechStack(null, backend, "Django"));
				techStackRepository.save(new TechStack(null, backend, "Flask"));
				techStackRepository.save(new TechStack(null, backend, "Ruby"));
				techStackRepository.save(new TechStack(null, backend, "php"));
				techStackRepository.save(new TechStack(null, backend, "Go"));
				techStackRepository.save(new TechStack(null, backend, "MySQL"));
				techStackRepository.save(new TechStack(null, backend, "MongoDB"));
				techStackRepository.save(new TechStack(null, frontend, "JavaScript"));
				techStackRepository.save(new TechStack(null, frontend, "TypeScript"));
				techStackRepository.save(new TechStack(null, frontend, "React"));
				techStackRepository.save(new TechStack(null, frontend, "Vue"));
				techStackRepository.save(new TechStack(null, frontend, "Svelte"));
				techStackRepository.save(new TechStack(null, frontend, "Nextjs"));
				techStackRepository.save(new TechStack(null, mobile, "Flutter"));
				techStackRepository.save(new TechStack(null, mobile, "Swift"));
				techStackRepository.save(new TechStack(null, mobile, "Kotlin"));
				techStackRepository.save(new TechStack(null, mobile, "ReactNative"));
				techStackRepository.save(new TechStack(null, mobile, "Unity"));
				techStackRepository.save(new TechStack(null, etc, "AWS"));
				techStackRepository.save(new TechStack(null, etc, "Docker"));
				techStackRepository.save(new TechStack(null, etc, "Kubernetes"));
				techStackRepository.save(new TechStack(null, etc, "Figma"));
				techStackRepository.save(new TechStack(null, etc, "Git"));
			}
		};
	}
}
