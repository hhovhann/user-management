package com.hhovhann.usermanagement.repositories;

import com.hhovhann.usermanagement.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    
    void deleteById(Long id);
}
