package com.example.TimeApp.Repository;

import com.example.TimeApp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public com.example.TimeApp.Entities.User getUserByUsername(String username);
}
