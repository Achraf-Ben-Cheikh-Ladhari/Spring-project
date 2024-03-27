package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.user.Role;
import com.ladharitech.ladhari.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    Optional<User> findById (UUID id);
}
