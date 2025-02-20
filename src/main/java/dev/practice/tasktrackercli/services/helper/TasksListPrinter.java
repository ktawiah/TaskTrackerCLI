package dev.practice.tasktrackercli.services.helper;

import dev.practice.tasktrackercli.model.Task;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TasksListPrinter {

    private static final String BORDER = "+-------+------------------------------------------+--------------+------------------+------------------+";
    private static final String HEADER = "| ID    | Description                              | Status       | Created Time     | Updated Time     |";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String TASK_FORMAT = "| %-5d | %-40s | %-12s | %-16s | %-16s |%n";
    private static final int DESCRIPTION_MAX_LENGTH = 40;

    public void printTaskList(List<Task> tasks) {
        // Print the top border, header, and bottom border
        printBorder();
        printHeader();
        printBorder();

        // Print each task
        tasks.forEach(this::printTask);

        // Print the bottom border after all tasks
        printBorder();
    }

    private void printBorder() {
        System.out.println(BORDER);
    }

    private void printHeader() {
        System.out.println(HEADER);
    }

    private void printTask(Task task) {
        System.out.printf(
                TASK_FORMAT,
                task.getId(),
                truncate(task.getDescription()),
                task.getStatus().toString(),
                task.getCreatedAt().format(DATE_FORMATTER),
                task.getUpdatedAt().format(DATE_FORMATTER)
        );
    }

    private String truncate(String description) {
        if (description == null || description.isEmpty()) {
            return "N/A"; // Return a placeholder if the description is null or empty
        }

        // Truncate the description if it exceeds the maximum length
        if (description.length() <= DESCRIPTION_MAX_LENGTH) {
            return description;
        }
        return description.substring(0, DESCRIPTION_MAX_LENGTH - 3) + "...";
    }
}
