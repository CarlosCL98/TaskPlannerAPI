package edu.eci.TaskPlanner;

import edu.eci.TaskPlanner.Model.Status;
import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Persistence.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskPlannerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void itShouldFindAllUsers() {
		userRepository.deleteAll();
		userRepository.save(new User(
				1,"Carlos Medina","CarlosCL98","c@m.com","123456",null
		));
		userRepository.save(new User(
				2,"Juan Medina","juanhalo2004","j@m.com","123456",null
		));

		int count = 0;
		System.out.println("Users found with findAll():");
		System.out.println("-------------------------------");
		for (User user : userRepository.findAll()) {
			System.out.println(user);
			count++;
		}
		System.out.println("-------------------------------");
		System.out.println();
		assertTrue(count == 2);
	}

	@Test
	public void itShouldFindUserByEmail() {
		userRepository.deleteAll();
		userRepository.save(new User(
				1,"Carlos Medina","CarlosCL98","c@m.com","123456",null
		));
		userRepository.save(new User(
				2,"Juan Medina","juanhalo2004","j@m.com","123456",null
		));
		System.out.println("Users found with findByEmail():");
		System.out.println("-------------------------------");
		String email1 = "c@m.com";
		User user1 = userRepository.findByEmail(email1);
		System.out.println(user1);
		assertTrue(email1.equals(user1.getEmail()));
		String email2 = "j@m.com";
		User user2 = userRepository.findByEmail(email2);
		System.out.println(user2);
		assertTrue(email2.equals(user2.getEmail()));
		System.out.println("-------------------------------");
		System.out.println();
	}

	@Test
	public void itShouldFindUserByUsername() {
		userRepository.deleteAll();
		userRepository.save(new User(
				1,"Carlos Medina","CarlosCL98","c@m.com","123456",null
		));
		userRepository.save(new User(
				2,"Juan Medina","juanhalo2004","j@m.com","123456",null
		));
		System.out.println("Users found with findByUsername():");
		System.out.println("-------------------------------");
		String username1 = "CarlosCL98";
		User user1 = userRepository.findByUsername(username1);
		System.out.println(user1);
		assertTrue(username1.equals(user1.getUsername()));
		String username2 = "juanhalo2004";
		User user2 = userRepository.findByUsername(username2);
		System.out.println(user2);
		assertTrue(username2.equals(user2.getUsername()));
		System.out.println("-------------------------------");
		System.out.println();
	}

	@Test
	public void itShouldFindAllTasks() {
		taskRepository.deleteAll();
		taskRepository.save(new Task(
				1,"React App","Create the react app base to make the task planner app.",
				"2019-09-16", Status.COMPLETE,null
		));
		taskRepository.save(new Task(
				2,"Implement Create Task View","Implement this view to allow create tasks.",
				"2019-09-16", Status.READY,null
		));

		int count = 0;
		System.out.println("Tasks found with findAll():");
		System.out.println("-------------------------------");
		for (Task task : taskRepository.findAll()) {
			System.out.println(task);
			count++;
		}
		System.out.println("-------------------------------");
		System.out.println();
		assertTrue(count == 2);
	}

	@Test
	public void itShouldFindTasksByResponsibleEmail() {
		userRepository.deleteAll();
		User user = new User(
				1,"Carlos Medina","CarlosCL98","c@m.com","123456",null
		);
		userRepository.save(user);
		taskRepository.deleteAll();
		taskRepository.save(new Task(
				1,"React App","Create the react app base to make the task planner app.",
				"2019-09-16", Status.COMPLETE, user
		));
		taskRepository.save(new Task(
				2,"Implement Create Task View","Implement this view to allow create tasks.",
				"2019-09-16", Status.READY, user
		));

		int count = 0;
		System.out.println("Tasks found with findByResponsible_Email():");
		System.out.println("-------------------------------");
		for (Task task : taskRepository.findByResponsible_Email("c@m.com")) {
			System.out.println(task);
			count++;
		}
		System.out.println("-------------------------------");
		System.out.println();
		assertTrue(count == 2);
	}
}
