package edu.eci.TaskPlanner.Persistence;

import edu.eci.TaskPlanner.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String email);

    User findByUsername(String username);
}
