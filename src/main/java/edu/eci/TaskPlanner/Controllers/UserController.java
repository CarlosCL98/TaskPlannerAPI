package edu.eci.TaskPlanner.Controllers;

import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/taskPlanner")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersList() {
        List<User> users = userService.getUsersList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
