package com.sample.spring.todos.todolist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ugur.acik
 */
@Data
@NoArgsConstructor
public class TodoListRequest {

    private String name;

    public TodoListRequest(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
