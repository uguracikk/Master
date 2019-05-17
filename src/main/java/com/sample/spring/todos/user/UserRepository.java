package com.sample.spring.todos.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author ugur.acik
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
