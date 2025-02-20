package dev.practice.tasktrackercli.services;

import dev.practice.tasktrackercli.model.Task;
import dev.practice.tasktrackercli.model.TaskStatus;

import java.util.List;


public interface TaskServices {

    public String createNewTask(String taskDescription, String taskStatus);

    public void listTasksByStatus(String taskStatus);

    public String markTaskByStatus(Long id, TaskStatus status);

    public String deleteTask(Long id);

    public void retrieveById(Long id);

    public void showTasks();

    public void listByStatusOtherThan(String status);

}
