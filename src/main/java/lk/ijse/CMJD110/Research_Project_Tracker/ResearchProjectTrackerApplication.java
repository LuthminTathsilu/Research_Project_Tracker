package lk.ijse.CMJD110.Research_Project_Tracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ResearchProjectTrackerApplication {

	public static void main(String[] args) {

        SpringApplication.run(ResearchProjectTrackerApplication.class, args);
	}
    @Bean
    public ModelMapper modelMapper(){
         return new ModelMapper();
    }

}
