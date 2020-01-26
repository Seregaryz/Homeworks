package com.example.homeworks.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.homeworks.todo.ToDoItem

@Dao
interface ToDoDAO {

    @Query("INSERT INTO todo (title, description, date) VALUES(:title, :description, :date)")
    suspend fun save(title: String, description: String, date: String)

    @Query("SELECT * FROM todo")
    suspend fun getToDoList(): List<ToDoItem>

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteToDo(id: Int)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getToDoById(id: Int): ToDoItem?
}