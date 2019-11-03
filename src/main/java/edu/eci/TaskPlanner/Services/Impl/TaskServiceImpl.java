package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.TaskRepository;
import edu.eci.TaskPlanner.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasksList() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String taskId) {
        return taskRepository.findById(Integer.parseInt(taskId));
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findByResponsible_Id(Integer.parseInt(userId));
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) {
        Task task = taskRepository.findById(Integer.parseInt(taskId));
        task.setResponsible(user);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task createTask(Task task) {
        task.setId((int) (taskRepository.count() + 1));
        return taskRepository.save(task);
    }

    @Override
    public void removeTask(String taskId) {
        taskRepository.deleteById(Integer.parseInt(taskId));
    }

    @Override
    public Task updateTask(Task task) {
        taskRepository.save(task);
        return task;
    }
}
