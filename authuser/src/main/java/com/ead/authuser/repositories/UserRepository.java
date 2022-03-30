package com.ead.authuser.repositories;

import java.util.UUID;

import com.ead.authuser.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    
}
