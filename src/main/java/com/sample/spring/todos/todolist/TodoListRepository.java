package com.sample.spring.todos.todolist;

import org.springframework.data.repository.CrudRepository;

import com.sample.spring.todos.user.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ugur.acik
 */
public interface TodoListRepository extends CrudRepository<TodoList, Long> {
    List<TodoList> findAllByOwner(User owner);

    TodoList findOneByIdAndOwner(Long id, User owner);

    @Transactional
    void deleteByIdAndOwner(Long id, User owner);
}
