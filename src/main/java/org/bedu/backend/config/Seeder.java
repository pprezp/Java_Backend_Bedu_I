package org.bedu.backend.config;

import org.bedu.backend.entities.UsersEntity;
import org.bedu.backend.repositories.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Seeder implements CommandLineRunner {

    private final IUsersRepository repository;

    @Autowired
    public Seeder(IUsersRepository repository){
        this.repository = repository;
    }

    public void run(String[] args){

        UsersEntity user = new UsersEntity();
        user.setId(1L);
        user.setName("Pablo");
        user.setLastName("Perez");
        user.setEmail("pablo.prezparedes@outlook.com");
        user.setPassword("PASS");

        repository.save(user);
    }

}
