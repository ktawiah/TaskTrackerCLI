package dev.practice.tasktrackercli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.practice.tasktrackercli.model.Task;
import dev.practice.tasktrackercli.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private static final Logger logger = LoggerFactory.getLogger(TaskRepositoryImpl.class); // Logger instance
    private final File jsonFile;
    private final ObjectMapper objectMapper;

    public TaskRepositoryImpl() throws IOException {
        String jsonFilePath = Paths.get("java", "dev", "practice", "tasktrackercli", "data", "tasks.json").toString();
        File file = new File(jsonFilePath);

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("tasks.json file creation failed. Check path, %s and try again.".formatted(jsonFilePath));
            }
        }
        this.jsonFile = file;
        this.objectMapper = new ObjectMapper();
    }

    private List<Task> readTasksFromFile() {
        try {
            if (jsonFile.length() == 0) {
                return List.of();
            }
            return objectMapper.readValue(jsonFile, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            logger.error("Error reading tasks from file: {}", e.getMessage(), e); // Log the error
            return List.of();
        }
    }

    private void writeTasksToFile(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, tasks);
        } catch (IOException e) {
            logger.error("Error writing tasks to file: {}", e.getMessage(), e); // Log the error
        }
    }

    @Override
    public String addTask(Task task) {
        List<Task> tasks = readTasksFromFile();
        tasks.add(task);
        writeTasksToFile(tasks);
        return "Task added successfully.";
    }

    @Override
    public String updateTask(Long id, Task task) {
        List<Task> tasks = readTasksFromFile();
        Optional<Task> taskToUpdate = tasks.stream().filter(t -> t.getId().equals(id)).findFirst();

        if (taskToUpdate.isPresent()) {
            tasks.remove(taskToUpdate.get());
            tasks.add(task);
            writeTasksToFile(tasks);
            return "Task updated successfully.";
        }
        return "Task not found.";
    }

    @Override
    public String deleteTask(Long id) {
        List<Task> tasks = readTasksFromFile();
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));

        if (removed) {
            writeTasksToFile(tasks);
            return "Task deleted successfully.";
        }
        return "Task not found.";
    }

    @Override
    public Task getTaskById(Long id) {
        List<Task> tasks = readTasksFromFile();
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Task> getAllTasks() {
        return readTasksFromFile();
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = readTasksFromFile();
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .toList();
    }
}

