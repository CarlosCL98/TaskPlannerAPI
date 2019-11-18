package edu.eci.TaskPlanner;

import com.mongodb.client.gridfs.model.GridFSFile;
import edu.eci.TaskPlanner.Config.JwtFilter;
import edu.eci.TaskPlanner.Model.Status;
import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.AppConfiguration;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;
import java.util.List;

@Controller
@SpringBootApplication
public class TaskPlannerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

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
