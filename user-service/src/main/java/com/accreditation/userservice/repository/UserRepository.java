package com.accreditation.userservice.repository;

import com.accreditation.userservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDetail, Integer> {

    @Query(value = "select u from UserDetail u where u.email= :email")
    Optional<UserDetail> findUserByEmail(String email);

    @Query(value = "select u from UserDetail u where u.username= :username")
    Optional<UserDetail> findUserByUsername(String username);


}
