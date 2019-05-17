package com.sample.spring.todos.todolist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.spring.todos.todolist.TodoItem;
import com.sample.spring.todos.todolist.TodoItemRepository;
import com.sample.spring.todos.todolist.TodoList;
import com.sample.spring.todos.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

/**
 * @author ugur.acik
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoItemRepositoryTests {

    @Autowired
    TodoItemRepository repository;

    @Autowired
    private TestEntityManager entityManager;
    private User owner;
    private TodoList todoList;

    @Before
    public void setUp() throws Exception {
        owner = entityManager.find(User.class, 1L);
        todoList = entityManager.find(TodoList.class, 1L);
    }

    @Test
    public void testCreate() {
        TodoItem todoItem = new TodoItem("buy milk" ,todoList, owner);
//        todoItem.setName("buy milk");
//        todoItem.setList(todoList);
//        todoItem.setOwner(owner);
        repository.save(todoItem);

        TodoItem loadedItem = repository.findOne(7L);
        assertThat(loadedItem.getName()).isEqualTo("buy milk");

        TodoList loadedTodoList = entityManager.find(TodoList.class, 1L);
        assertThat(loadedTodoList.getItems()).contains(loadedItem);
    }

    @Test
    public void testGet() {
        TodoItem todoItem = repository.findOneByIdAndListAndOwner(1L, todoList, owner);
        assertThat(todoItem.getName()).isEqualTo("Item 1");
    }

    @Test
    public void testDelete() {
        repository.deleteByIdAndListAndOwner(1L, todoList, owner);
        assertNull(repository.findOne(1L));
    }
}
