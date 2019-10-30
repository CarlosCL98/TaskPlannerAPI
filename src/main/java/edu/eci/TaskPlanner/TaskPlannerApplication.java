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
public class TaskPlannerApplication implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is("lion.jpeg")));
        URL url = new URL("https://i.dailymail.co.uk/i/pix/tm/2007/07/lionking1807_468x325._to_468x312jpeg");
        gridFsTemplate.store(url.openStream(), "lion.jpeg",  "image/jpeg");
        System.out.println("----------------Lion Image-----------------");
        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is("lion.jpeg")));
        System.out.println("File Exists: " + (file!=null));
        System.out.println("-------------------------------------------");
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        userRepository.deleteAll();
        User user1 = new User(1, "Carlos Medina", "CarlosCL98", "c@m.com", "123456", null);
        userRepository.save(user1);
        userRepository.save(new User(2, "Juan Medina", "juanhalo2004", "j@m.com", "123456", null));
        userRepository.save(new User(3, "Sofia Gomez", "sg9852", "s_g@m.com", "123456", null));
		User user4 = new User(4, "Kevin Alvarez", "kevAlvarez24", "k@m.com", "123456", null);
        userRepository.save(user4);
        userRepository.save(new User(5, "Hernan Moreno", "HenMor32", "hm@m.com", "123456", null));
        userRepository.save(new User(6, "Luisa Perez", "LuisaPG", "l@m.com", "123456", null));
        userRepository.save(new User(7, "Juan Acosta", "juanAc0st4", "ja@m.com", "123456", null));
        userRepository.save(new User(8, "Carlos Rodriguez", "CarlosRog98", "c_rog@m.com", "123456", null));
        userRepository.save(new User(9, "Ana Guevara", "AniGuevara", "a-guev@m.com", "123456", null));
        userRepository.save(new User(10, "Flor Torres", "florres", "florres@m.com", "123456", null));
        taskRepository.deleteAll();
        taskRepository.save(new Task(1, "React App 1", "Create the react app base to make the task planner app.", "2019-09-16", Status.COMPLETE, null));
		taskRepository.save(new Task(2, "React App 2", "Create the react app base to make the task planner app.", "2019-10-1", Status.READY, null));
		taskRepository.save(new Task(3, "React App 3", "Create the react app base to make the task planner app.", "2019-10-5", Status.COMPLETE, user1));
		taskRepository.save(new Task(4, "React App 4", "Create the react app base to make the task planner app.", "2019-10-8", Status.COMPLETE, null));
		taskRepository.save(new Task(5, "React App 5", "Create the react app base to make the task planner app.", "2019-10-16", Status.READY, user1));
		taskRepository.save(new Task(6, "React App 6", "Create the react app base to make the task planner app.", "2019-10-16", Status.COMPLETE, null));
		taskRepository.save(new Task(7, "React App 7", "Create the react app base to make the task planner app.", "2019-10-16", Status.IN_PROGRESS, user1));
		taskRepository.save(new Task(8, "React App 8", "Create the react app base to make the task planner app.", "2019-10-25", Status.READY, null));
		taskRepository.save(new Task(9, "React App 9", "Create the react app base to make the task planner app.", "2019-10-30", Status.COMPLETE, user1));
		taskRepository.save(new Task(10, "React App 10", "Create the react app base to make the task planner app.", "2019-11-2", Status.READY, null));
		taskRepository.save(new Task(11, "React App 11", "Create the react app base to make the task planner app.", "2019-11-3", Status.COMPLETE, user1));
		taskRepository.save(new Task(12, "React App 12", "Create the react app base to make the task planner app.", "2019-11-16", Status.IN_PROGRESS, null));
		taskRepository.save(new Task(13, "React App 13", "Create the react app base to make the task planner app.", "2019-11-16", Status.READY, null));
		taskRepository.save(new Task(14, "React App 14", "Create the react app base to make the task planner app.", "2019-11-16", Status.COMPLETE, user4));
		taskRepository.save(new Task(15, "React App 15", "Create the react app base to make the task planner app.", "2019-11-26", Status.READY, null));
		taskRepository.save(new Task(16, "React App 16", "Create the react app base to make the task planner app.", "2019-11-26", Status.COMPLETE, user4));
		taskRepository.save(new Task(17, "React App 17", "Create the react app base to make the task planner app.", "2019-09-2", Status.IN_PROGRESS, null));
		taskRepository.save(new Task(18, "React App 18", "Create the react app base to make the task planner app.", "2019-09-98", Status.COMPLETE, null));
		taskRepository.save(new Task(19, "React App 19", "Create the react app base to make the task planner app.", "2019-09-14", Status.COMPLETE, user4));
		taskRepository.save(new Task(20, "React App 20", "Create the react app base to make the task planner app.", "2019-09-32", Status.IN_PROGRESS, null));
		taskRepository.save(new Task(21, "React App 21", "Create the react app base to make the task planner app.", "2019-09-16", Status.READY, user4));
		taskRepository.save(new Task(22, "React App 22", "Create the react app base to make the task planner app.", "2019-12-1", Status.IN_PROGRESS, null));
		taskRepository.save(new Task(23, "React App 23", "Create the react app base to make the task planner app.", "2019-12-10", Status.READY, user4));
		taskRepository.save(new Task(24, "React App 24", "Create the react app base to make the task planner app.", "2019-12-15", Status.COMPLETE, user4));
		taskRepository.save(new Task(25, "React App 25", "Create the react app base to make the task planner app.", "2019-12-27", Status.COMPLETE, null));
        Query query = new Query();
		// 1. Todos that the dueDate has expire.
        query.addCriteria(Criteria.where("dueDate").lt("2019-10-23"));
        List<Task> expiredTasks = mongoOperation.find(query, Task.class);
        System.out.println("----------------Expired Tasks:-----------------");
        for (Task expiredTask: expiredTasks) {
            System.out.println(expiredTask);
        }
        System.out.println("-----------------------------------------------");
        // 2. Todos that are assigned to given user and have priority greater equal to 5 (Status Ready)
        query = new Query();
        query.addCriteria(Criteria.where("responsible").is(user1).and("status").is("READY"));
        List<Task> tasksReady = mongoOperation.find(query, Task.class);
        System.out.println("----------------Tasks with responsible and READY:-----------------");
        for (Task task: tasksReady) {
            System.out.println(task);
        }
        System.out.println("------------------------------------------------------------------");
        // 3. List users that have assigned more than 2 Todos.
        query = new Query();
        query.addCriteria(Criteria.where("responsible").is("c@m.com").and("status").is("READY"));
        List<User> users = mongoOperation.find(query, User.class);
        System.out.println("----------------Users with more than 2 tasks:-----------------");
        for (User user: users) {
            System.out.println(user);
        }
        System.out.println("--------------------------------------------------------------");
        // 4. Todolist that contains the description with a length greater than 30 characters.
        query = new Query();
        query.addCriteria(Criteria.where("description").size(30));
        List<Task> tasks = mongoOperation.find(query, Task.class);
        System.out.println("----------------Tasks with a large description:-----------------");
        for (Task task: tasks) {
            System.out.println(task);
        }
        System.out.println("--------------------------------------------------------------");*/
    }
}
