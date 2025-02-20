package dev.practice.tasktrackercli.services;

import dev.practice.tasktrackercli.model.Task;
import dev.practice.tasktrackercli.model.TaskStatus;
import dev.practice.tasktrackercli.repository.TaskRepositoryImpl;
import dev.practice.tasktrackercli.services.helper.TasksListPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Command(command = "taskly")
public class TaskServicesImpl implements TaskServices  {
    @Autowired
    private TaskRepositoryImpl repository;

    private static final TasksListPrinter tasksListPrinter = new TasksListPrinter();

    @Command(
            command = "create-task",
            description = "Adds a new task."
    )
    @Override
    public String createNewTask(
            @Option(longNames = "description", shortNames = 'd') String taskDescription,
            @Option(longNames = "status", shortNames = 's', defaultValue = "NOT_STARTED") String taskStatus
    ) {
        Task task = new Task(taskDescription, TaskStatus.valueOf(taskStatus), LocalDateTime.now(), LocalDateTime.now());
        return repository.addTask(task);
    }

    @Command(
            command = "get-by-id",
            description = "Retrieve task by ID"
    )
    @Override
    public void retrieveById(@Option(longNames = "id", shortNames = 'i') Long id) {
        Task task = repository.getTaskById(id);
        tasksListPrinter.printTaskList(List.of(task));
    }

    @Command(
            command = "list-tasks",
            description = "Retrieves all tasks."
    )
    @Override
    public void showTasks() {
        List<Task> tasks = repository.getAllTasks();
        tasksListPrinter.printTaskList(tasks);
    }


    @Command(
            command = "list-by-status-other-than",
            description = "Retrieves tasks by task by status other than the one specified"
    )
    @Override
    public void listByStatusOtherThan(@Option(longNames = "status", shortNames = 's') String status) {
        List<Task> tasks = repository.getAllTasks();
        List<Task> tasksRetrieved = tasks.stream()
                .filter(task -> !task.getStatus().equals(TaskStatus.valueOf(status)))
                .toList();
        tasksListPrinter.printTaskList(tasksRetrieved);
    }

    @Command(
            command = "list-by-status",
            description = "Retrieves tasks by status specified"
    )
    @Override
    public void listTasksByStatus(@Option(longNames = "status", shortNames = 's') String taskStatus) {
        List<Task> taskList = repository.getTasksByStatus(TaskStatus.valueOf(taskStatus));
        tasksListPrinter.printTaskList(taskList);
    }

    @Command(
            command = "mark-as",
            description = "Marks task based on status specified (TODO, NOT_STARTED, DONE)"
    )
    @Override
    public String markTaskByStatus(
            @Option(longNames = "id", shortNames = 'i') Long id,
            @Option(longNames = "status", shortNames = 's') TaskStatus status
    ) {
        Task taskToUpdate = repository.getTaskById(id);

        if (taskToUpdate == null) {
            return "Task with ID %s does not exist".formatted(id);
        }
        taskToUpdate.setStatus(status);
        taskToUpdate.setUpdatedAt(LocalDateTime.now());
        return repository.updateTask(id, taskToUpdate) ;
    }

    @Command(
            command = "delete-task",
            description = "Deletes task by ID"
    )
    @Override
    public String deleteTask(@Option(longNames = "id", shortNames = 'i') Long id) {
        return repository.deleteTask(id);
    }


}