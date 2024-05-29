package com.dev.explore.repo;

import com.dev.explore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = ?1")
    Page<User> findAllAdmins(Pageable pageable,String admin );
}
