package com.sample.spring.todos.todolist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.spring.todos.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ugur.acik
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="todo_list")
public class TodoList {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@OneToMany(mappedBy = "list",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<TodoItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "OWNER_USER_ID")
    @JsonIgnore
    private User owner;

    public TodoList(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

//    public TodoList() {
//		// TODO Auto-generated constructor stub
//	}

	public static TodoList from(TodoListRequest todoListRequest, User user) {
        return new TodoList(todoListRequest.getName(), user);
    }

    public void merge(TodoListRequest request) {
        setName(request.getName());
    }
}
