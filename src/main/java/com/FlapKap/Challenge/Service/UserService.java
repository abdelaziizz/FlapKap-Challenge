package com.FlapKap.Challenge.Service;

import com.FlapKap.Challenge.Model.User;
import com.FlapKap.Challenge.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User createUser(String username, String password, String role) {
        try {
            User user = new User(username, password, 0, role);
            System.out.println(user);
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public String updateUser(long id, String username, String password, float deposit, String role) {
        try {
            User user = userRepo.getById(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setDeposit(deposit);
            user.setRole(role);
            userRepo.save(user);
            return "User Updated";
        } catch (Exception e) {
            return "Error Updating User";
        }
    }

    public String deleteUser(long id) {
        try {
            User user = userRepo.getById(id);
            userRepo.delete(user);
            return "User deleted";
        } catch (Exception e) {
            return "Error deleting user";
        }
    }

}
