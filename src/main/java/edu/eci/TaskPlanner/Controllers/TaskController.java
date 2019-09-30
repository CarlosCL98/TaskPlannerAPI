package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Model.Task;
import edu.eci.TaskPlanner.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/taskPlanner")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/v1/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasksList() {
        List<Task> tasks = taskService.getTasksList();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
