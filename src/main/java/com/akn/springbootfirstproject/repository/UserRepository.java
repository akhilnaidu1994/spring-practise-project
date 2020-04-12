package com.akn.springbootfirstproject.repository;

import com.akn.springbootfirstproject.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser getByUsername(String username);
}
