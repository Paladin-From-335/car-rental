package com.github.carrental.repo;

import com.github.carrental.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user_table WHERE driver_license=:license", nativeQuery = true)
    Optional<User> findByLicense(String license);
}
