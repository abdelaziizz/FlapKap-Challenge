package com.FlapKap.Challenge.Controller;

import com.FlapKap.Challenge.Model.Product;
import com.FlapKap.Challenge.Model.User;
import com.FlapKap.Challenge.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/createUser")
    public User createProduct(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role) {

        return userService.createUser(username, password, role);
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam float deposit,
                             @RequestParam String role) {
        System.out.println(id);
        User user = new User(username, password, deposit, role);
        System.out.println(user);

        return userService.updateUser(id, username, password, deposit, role);
        //username, password, deposit, role

    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

}
