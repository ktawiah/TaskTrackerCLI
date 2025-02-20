package dev.practice.tasktrackercli.repository;

import dev.practice.tasktrackercli.model.Task;
import dev.practice.tasktrackercli.model.TaskStatus;

import java.util.List;

interface TaskRepository {
    String addTask(Task task);

    String updateTask(Long id, Task task);

    String deleteTask(Long id);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    List<Task> getTasksByStatus(TaskStatus status);

}
