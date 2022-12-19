package org.bedu.backend.repositories;

import org.bedu.backend.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsersRepository extends JpaRepository <UsersEntity, Long> {
    List<UsersEntity> findAll();
    UsersEntity findByEmail( @Param("email") String email);
}
