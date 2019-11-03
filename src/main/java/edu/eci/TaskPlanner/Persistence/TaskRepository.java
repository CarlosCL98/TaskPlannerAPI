package edu.eci.TaskPlanner.Persistence;

import edu.eci.TaskPlanner.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, Integer> {

    Task findById(int id);

    List<Task> findByResponsible_Email(String responsibleEmail);

    List<Task> findByResponsible_Id(int userId);
}
