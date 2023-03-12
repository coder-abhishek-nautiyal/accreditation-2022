package com.accreditation.userservice.repository;

import com.accreditation.userservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDetail, Integer> {
    Optional<UserDetail> findByUsername(String username);

}
