package com.example.todoapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.domain.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: TodoRepository
): ViewModel() {
    var toDo: ToDo by mutableStateOf(ToDo(0, "", false))
        private set


    val getAllToDos: Flow<List<ToDo>>  = repository.getAllTodos()

    private var deletedTodo: ToDo? = null


    fun insertTodo(todo:ToDo){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertTodo(toDo = todo)

        }
    }

    fun updateTodo(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(toDo = todo)
        }
    }

    fun deleteTodo(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            deletedTodo = todo
            repository.deleteTodo(toDo = todo)
        }
    }

    fun undoDeleteTodo(){
        deletedTodo?.let { toDo ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertTodo(toDo = toDo)
            }
        }
    }


    fun getTodoById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            toDo = repository.getTodoById(id  = id)
        }
    }

    fun updateTask(newValue: String){
        toDo = toDo.copy(task = newValue)

    }

    fun updateIsImportant(newValue: Boolean){
        toDo = toDo.copy(isImportant = newValue)
    }
}
