package edu.eci.TaskPlanner.Services;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;

import java.util.List;

public interface TaskService {

    List<Task> getTasksList();

    Task getTaskById(String id);

    List<Task> getTasksByUserId(String userId);

    Task assignTaskToUser(String taskId, User user);

    void removeTask(String taskId);

    Task updateTask(Task task);
}
