package com.FlapKap.Challenge.Repository;


import com.FlapKap.Challenge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String name);

    List<User> findAllByRole (String role);

}