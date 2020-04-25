package com.hhovhann.usermanagement;

import com.hhovhann.usermanagement.entities.User;
import com.hhovhann.usermanagement.repositories.UserRepository;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public Initializer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        UnifiedSet.newSetWith(new User("Hayk Hovhannisyan", "hh@gmail.com"), new User("George Adams", "ga@gmail.com"), new User("Rain Gigs", "rg@gmail.com")).forEach(entity ->repository.save(entity));
        repository.findAll().forEach(System.out::println);
    }
}
