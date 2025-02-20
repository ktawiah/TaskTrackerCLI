# 🗂️ Taskly CLI

This project is a command-line task management tool built with Spring Shell. It allows you to create, update, delete, and list tasks, 
which are stored in a JSON file. The tool supports filtering tasks by status and handles errors gracefully.

### ✨ Features
- ➕ Add Tasks: Create new tasks with a title, description, and status.
- 🔄 Update Tasks: Modify existing tasks by their ID.
- ❌ Delete Tasks: Remove tasks by their ID.
- 📋List Tasks: View all tasks or filter them by status (e.g., TODO, IN_PROGRESS, DONE).
- 💾Persistent Storage: Tasks are stored in a JSON file (tasks.json) for persistence across sessions.
- 🚨Error Handling: Handles invalid inputs, missing files, and JSON parsing errors.

### Getting Started 🚀

#### Prerequisites
- Java 17 or higher
- Gradle 7.x or higher

### How to Run
- Clone the repository:

```git
  git clone https://github.com/ktawiah/TaskTrackerCLI.git
```

- Navigate to the project directory:
```bash
  cd taskTrackerCLI
```

- Build the project using Gradle:
```bash
  ./gradlew build
```

```bash
  ./gradlew bootRun
```

### 📘 Usage
Once the application is running, you can use the following commands:
NB: Status Options = NOT_STARTED, COMPLETED, IN_PROGRESS
- ```taskly create-task --description "Complete project"```: Add a Task with Status
- ```taskly create-task --description "Complete project" --status TODO```: Add a Task without status
- ```taskly delete-task --id 1```: Delete a Task.
- ```taskly list-tasks```: List All Tasks
- ```taskly list-tasks-by-status --status IN_PROGRESS```: List Tasks by Status
- ```taskly list-by-status-other-than --status NOT_STATED```: Retrieves tasks by task by status other than the one specified
- ```taskly mark-as --status COMPLETED```: Marks task based on status specified

🐛 Troubleshooting
Common Issues
1. JSON File Not Found:
    - Ensure the tasks.json file exists in the src/main/java/dev/practice/tasktrackercli/data/ directory.
    - If the file is missing, the application will create it automatically.

2. Invalid JSON Format:
    - Ensure the tasks.json file contains valid JSON. For example:

    ```json
   [
        {
        "id": 1,
        "description": "Finish the Spring Shell project",
        "status": "TODO",
        "createdAt": "2025-02-20T02:06:43.980"
        "updatedAt": "2025-02-20T02:06:43.980"
        }
    ]
   ```

3. Java 8 Date/Time Error:
    - If you encounter errors related to ```java.time.LocalDateTime```, ensure the ```jackson-datatype-jsr310``` dependency is added 
      and the ```JavaTimeModule``` is registered with the ```ObjectMapper```.

Sample solution for the <a href="https://roadmap.sh/projects/task-tracker">Task Tracker </a> challenge from <a href="https://roadmap.sh">roadmap.sh</a>.