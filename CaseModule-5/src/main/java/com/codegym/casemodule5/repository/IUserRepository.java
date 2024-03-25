package com.codegym.casemodule5.repository;

import com.codegym.casemodule5.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM User where username= :username or email = :username or phone = :username",nativeQuery = true)
    Optional<User> getUserByUsernameOrEmailOrPhone(@Param("username")String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmailOrPhone(String username,String email, String phone);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
}
