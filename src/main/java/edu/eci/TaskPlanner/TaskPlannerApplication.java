package edu.eci.TaskPlanner;

import edu.eci.TaskPlanner.Config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@SpringBootApplication
public class TaskPlannerApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getIndex() {
		return new ResponseEntity<>("Task Planner API", HttpStatus.OK);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/taskPlanner/v1/users/*", "/taskPlanner/v1/tasks/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

}
