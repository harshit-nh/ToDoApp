package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao:TodoDao) {

    suspend fun insertTodo(toDo: ToDo) = dao.insertTodo(todo = toDo)

    suspend fun updateTodo(toDo: ToDo) = dao.updateTodo(todo = toDo)

    suspend fun deleteTodo(toDo: ToDo) = dao.deleteTodo(todo = toDo)

    suspend fun getTodoById(id: Int):ToDo = dao.getTodoById(id = id)

    fun getAllTodos(): Flow<List<ToDo>> = dao.getAllTodos()

}