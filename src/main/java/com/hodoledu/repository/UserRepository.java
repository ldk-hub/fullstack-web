package com.hodoledu.repository;

import com.hodoledu.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAndPw(String email, String pw);

    Optional<User> findByEmail(String signup);
}
