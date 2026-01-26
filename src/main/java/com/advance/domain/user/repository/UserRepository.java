package com.advance.domain.user.repository;

import com.advance.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.username = :username")
    void updateUserEmailWithJpql(@Param("username") String username, @Param("email") String email);

    @Modifying
    @Query("DELETE FROM User u WHERE u.username = :username")
    void deleteUserByUsernameWithJpql(@Param("username") String username);
}
