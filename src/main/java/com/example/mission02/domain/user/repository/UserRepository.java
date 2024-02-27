package com.example.mission02.domain.user.repository;

import com.example.mission02.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhone(String phone);

    boolean existsByIdentification(String identification);
}
